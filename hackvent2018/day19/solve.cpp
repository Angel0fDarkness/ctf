#include <iostream>
#include <fstream>

using namespace std;

class Program
{
    const unsigned char enc_flag[30] = { 0xC1, 0xF2, 0x74, 0x68, 0xF3, 0x51, 0xBC, 0xF0, 0x00, 0x87, 0xAB, 0x8A, 0xA0, 0xD1, 0x57, 0xE3, 0xDE, 0x71, 0x12, 0xF3, 0x72, 0x85, 0xEB, 0x2F, 0x87, 0xA7, 0xAB, 0xAB, 0xFF, 0x74 };
    const unsigned char characters[62] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    static long cnt;
    ofstream flagFile;

    public:
        Program()
        {
            flagFile.open("flags.txt", ios::out | ios::trunc);
        }

        ~Program()
        {
            flagFile.close();
            cout << "Finished after " << cnt << " guesses." << endl;
        }

        int computeModconst(string prefix)
        {
            int modconst = 165;
            for (int i = 0; i < prefix.length(); i++)
            {
                unsigned char c = prefix[i];
                int x = c * modconst + 1337;
                unsigned char z = (x % 255);
                modconst = z;
            }
            return modconst;
        }

        inline bool validateChar(unsigned char c)
        {
            return ((c >= 48 && c <= 57) || // 0-9
                    (c >= 65 && c <= 90) || // A-Z
                    (c >= 97 && c <= 122)); // a-z
        }

        bool checkFlag(string prefix, string &flag)
        {
            int modconst = 165;
            long y = 0;
            unsigned char t[15];
            for (int i = 0; i < prefix.length(); i++)
            {
                unsigned char c = prefix[i];
                int x = c * modconst + 1337;
                y = y + x + c;
                unsigned char z = (x % 255);
                modconst = z;
                t[i] = z;
            }

            if (y == 217675)
            {
              for (int i = 0; i < sizeof(enc_flag) - 1; i++)
              {
                  flag  += (unsigned char) (enc_flag[i] ^ t[i % 15]);
              }
              return true;
            }
            return false;
        }

        void DiveIn(string prefix, int modconst)
        {
            if (++cnt % 1000000 == 0)
            {
                // Only for status messages
                cout << "Guess " << cnt << ": " << prefix << endl;
            }

            if (prefix.length() == 15)
            {
                // Check flag
                string flag = "";
                if (checkFlag(prefix, flag))
                {
                  flagFile << "Possible flag: " << prefix << " => " << flag << endl;
                }

                return;
            }

            for (int i = 0; i < sizeof(characters); i++)
            {
                unsigned char c = characters[i]; // 87
                int x = c * modconst + 1337; // 15692
                unsigned char z = (x % 255); // 137

                // Try decode
                unsigned char c1 = (enc_flag[prefix.length()] ^ z); // 72
                unsigned char c2 = (enc_flag[prefix.length() + 15] ^ z); // 106

                //cout << prefix << " + " << c << "(" << (int)c << "): " << x << "," << (int)z << " -> " << c1 << "," << c2 << endl;

                int pos = prefix.length() + 1;
                if (pos == 1)
                {
                    // First character is 'H'
                    if ((c1 == 'H') && validateChar(c2))
                    {
                        string new_prefix = prefix;
                        new_prefix += c;
                        DiveIn(new_prefix, z);
                    }
                    continue;
                }
                if (pos == 2)
                {
                    // Second character is 'V'
                    if ((c1 == 'V') && validateChar(c2))
                    {
                        string new_prefix = prefix;
                        new_prefix += c;
                        DiveIn(new_prefix, z);
                    }
                    continue;
                }
                if (pos == 3)
                {
                    // Third character is '1'
                    if ((c1 == '1') && validateChar(c2))
                    {
                        string new_prefix = prefix;
                        new_prefix += c;
                        DiveIn(new_prefix, z);
                    }
                    continue;
                }
                if (pos == 4)
                {
                    // Fourth character is '8'
                    if ((c1 == '8') && validateChar(c2))
                    {
                        string new_prefix = prefix;
                        new_prefix += c;
                        DiveIn(new_prefix, z);
                    }
                    continue;
                }
                if ((pos == 15) && c1 == '-')
                {
                    // c1 is a '-' again (each fifth character) but:
                    // Flag has only 29 characters, so c2 has to be ignored here.
                    string new_prefix = prefix;
                    new_prefix += c;
                    DiveIn(new_prefix, z);
                    continue;
                }
                if ((pos % 5) == 0)
                {
                    // Each fifth character is a '-'
                    if ((c1 == '-') && (c2 == '-')) {
                        string new_prefix = prefix;
                        new_prefix += c;
                        DiveIn(new_prefix, z);
                    }
                    continue;
                }
                if ( validateChar(c1) && validateChar(c2) )
                {
                    //cout << prefix << " + " << c << "(" << (int)c << "): " << x << "," << (int)z << " -> " << c1 << "," << c2 << endl;
                    // All others can be any character
                    string new_prefix = prefix;
                    new_prefix += c;
                    DiveIn(new_prefix, z);
                }
            }
        }
};


long Program::cnt = 0;


int main()
{
    Program p;
    //p.DiveIn("", 165);
    p.DiveIn("W3b45m", p.computeModconst("W3b45m"));
    return 0;
}
