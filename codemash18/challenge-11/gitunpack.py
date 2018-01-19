import sys
import zipfile
from git import Repo
import re
import os
import shutil



filename = sys.argv[1]
branch = "master"
password = ""
exit = False
while True:
    old_file = filename

    # Unzip the file (use pw if available)
    folder = filename.split('.')[0]
    zip_file = zipfile.ZipFile(filename, 'r')
    if len(password) > 0:
        zip_file.setpassword(password)
        password = ""
    zip_file.extractall('.')
    zip_file.close()

    # Get information about included repository
    print "##### Repo {}".format(folder)
    repo = Repo(folder)
    head = repo.head
    master = head.reference
    log = master.log()
    print "  found {} commits".format(len(log))

    # Loop through git log entries (commits)
    for entry in log:
        print "  SHA: {}".format(entry.newhexsha)
        commit = repo.commit(entry.newhexsha)
        tree = commit.tree
        print "    has {} subtrees and {} blobs".format(len(tree.trees), len(tree.blobs))
        
        # Check if Password is present in commit message
        found = re.search("Pass is (.*)$", entry.message)
        if found != None:
            password = found.groups(0)[0]
            print "    found Password: {}".format(password)
        
        # Loop through blobs if there are multiple inside a commit
        found = False
        for blob in tree.blobs:
            name = blob.path
            name_wo_ext = name.split('.')[0]
            # Check whether we have a zip file here (I think we are looking for zips)
            if name.endswith(".zip"):
                print "    File: {}".format(name)
                with open(name, 'w') as f:
                    # Write the blob to a zip
                    f.write(blob.data_stream.read())
                # Open the zipfile to check whether it includes a git repo
                test = zipfile.ZipFile(name, 'r')
                if name_wo_ext + "/.git/" in test.namelist():
                    filename = name
                    test.close()
                    found = True
                    break
                else:
                    test.close()
            else:
                # A non-zip file has been found, we have the flag
                print "  found non-zip file: {}".format(name)
                with open(name, 'w') as f:
                    f.write(blob.data_stream.read())
                exit = True
                found = True
                break
        
        # Stop the loop, we have a zip archive with a git repo inside
        if found:
            break

    # Remove old folders/files
    if old_file != sys.argv[1]: 
        os.remove(old_file)
    shutil.rmtree(folder)

    if exit:
        print "Finished."
        sys.exit(0)
