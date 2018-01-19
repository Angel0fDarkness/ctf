# Overview

Day | level | name | Used techniques | Flag
----|-------|------|-----------------|-----
[01](#challenge-01) | easy | Do you like my Style? | css | cm18-te94-1tuJ-ddx9-3dQO
[02](#challenge-02) | medium | Hobo Robo | http | cm18-kE1k-ZrV5-mTEu-pa5s
[03](#challenge-03) | hard | 1337 Riddler | script | cm18-Glz3-yM2k-h9i9-wntS
[04](#challenge-04) | easy | Super Eyesight | image | cm18-kjbJ-8Muo-e4q2-Z7Eh
[05](#challenge-05) | medium | Bools for fools | bool, qr | QR-Code:cm18-eJb2-mfTz-pIMu-oKaV
[06](#challenge-06) | easy | Witchcraft | hex | cm18-HewB-hP1r-Lyx9-epTN
[07](#challenge-07) | hard | Happy Eyes | crypto | cm18-FxVs-T5yc-YHNG-WicB
[08](#challenge-08) | medium | Lock | java | cm18-TEl9-sHKR-C01f-xkb8
[09](#challenge-09) | easy | Meow! | pdf | cm18-cxJg-MB4M-Q9E3-ZYlC
[10](#challenge-10) | medium | Chest | script | cm18-Ml2l-bUoC-vXXE-Fc8c
[11](#challenge-11) | hard | Bacon! | git | cm18-pCMy-5V56-K0TG-zBWT
[12](#challenge-12) | easy | On-site Challenge | none | On-site challenge
[13](#challenge-13) | hard | Alice | steganography | cm18-xZl2-eHC5-axW3-ZkZG
[14](#challenge-14) | medium | Security Regulations | none | cm18-bWh0-VIkC-cMf4-72jY
[15](#challenge-15) | hard | P.A.L.;. Login | js | cm18-zbIc-O4Zh-gmxl-r5J6


## Challenge 01
**Do you like my Style?**  
Every one is talking about styles. Sometimes its about what you wear and sometimes its about what you do.  
All I know is that the flag you want definitely has Style.
- Take a look at the pages CSS stylesheets
- At the end of style.css there is the flag.
- FLAG: cm18-te94-1tuJ-ddx9-3dQO

## Challenge 02
**Hobo Robo**  
Hobo Robo prepared a paper chase for you.  
Start link points to: https://codemash.hacking-lab.com/codemash/bots/bots.html
- When opening the link, you get redirected to Wikipedia https://en.wikipedia.org/wiki/C-3PO
- I used Firefox web developer tools to capture the Network traffic (you have to turn on the option "Persist Logs" or they get cleared when a new page is loaded)
- You will find an intermediate site that does the redirection (bots.html)
- You can also directly view the sourcecode of the page in the Response tab on the right after selecting the request.
- The page includes some javascript code:
```
eval(String.fromCharCode(105, 102, 32, 40, 33, 40, 110, 97, 118, 105, 103, 97, 116, 111, 114, 46, 117, 115, 101, 114, 65, 103, 101, 110, 116, 32, 61, 61, 61, 32, 39, 72, 111, 98, 111, 82, 111, 98, 111, 39, 41, 41, 32, 123, 32, 108, 111, 99, 97, 116, 105, 111, 110, 46, 114, 101, 112, 108, 97, 99, 101, 40, 39, 104, 116, 116, 112, 58, 47, 47, 101, 110, 46, 119, 105, 107, 105, 112, 101, 100, 105, 97, 46, 111, 114, 103, 47, 119, 105, 107, 105, 47, 67, 45, 51, 80, 79, 39, 41, 59, 125))
```
- Use the "Console" tab to evaluate the String part (without eval):
```
if (!(navigator.userAgent === 'HoboRobo')) { location.replace('http://en.wikipedia.org/wiki/C-3PO');}
```
- So the script checks the used UserAgent. You can user UserAgentSwitcher plugin or anything to change your UserAgent to be "HoboRobo"
- Now there is an image [robotbg](challenge-02/robotbg.jpg)
- Search for the text (which is written in the [Robot Interaction Language (ROILA)](http://roila.org/language-guide/vocabulary/)) to find the translation: "you must make word of addition two and two - this be name of page"
- So this is a hint to the name of the next page to check: https://codemash.hacking-lab.com/codemash/bots/four.html
- There is the next image [robotbg2](challenge-02/robotbg2.jpg) which shows the word "metae"..
- As metae does not lead to anything, check the page to find a meta description of the image:
```
<meta name="description" content="Robots talk in ROILA language: eman egap eht esrever tsum">
```
- The last part is just reversed: "must reverse the page name"
- So the next page name is https://codemash.hacking-lab.com/codemash/bots/ruof.html
- Which has another image [robotbg3](challenge-02/robotbg3?1337807.jpg) including the flag :)
- FLAG: cm18-kE1k-ZrV5-mTEu-pa5s

## Challenge 03
**1337 Riddler**  
1337 r1ddler h4s a puzzl3 f0r u 2 solve!  
H3 1s l1st3n1ng 0n th3 BEST p0r7 on this s3rv3r!
- Ok, what is the best port? 1337? No, nothing there.. BEST in leetspeak is 8357 and yes, there is someone listening on this port!
- Connecting via netcat shows a prompt:
```
nc codemash.hacking-lab.com 8357
  Make an educated guess, dude:
```
- After entering "test" you get an error `I need 20 digits, dude!`
- Ok, so we have to feed him 20 digits, lets try with *11111111111111111111*:
`0<`
- Hmm, what does the output mean? Lets try different values for the first digit. I assume the response value to be how many digits are correct.
- Yes, when we have 7 as first digit, we get `1<`!
- The < / > sign signals whether the digit you entered is less or greater than the right one
- Use the [script](challenge-03/riddler.py) to get the right value
- Right value: 78025928232920712967
- FLAG: cm18-Glz3-yM2k-h9i9-wntS

## Challenge 04
**Super Eyesight**  
Can you see what others cannot?  
Here is an image to prove your super eyesight.
- Download the [image](challenge-04/image.jpg)
- First check `file` and `binwalk` on the image without success
- Play around with different filters in GIMP
- Using *threshold* it is possible to find the flag in the lower right corner
- FLAG: cm18-kjbJ-8Muo-e4q2-Z7Eh

## Challenge 05
**Bools for fools**  
Calculate this!  
```
((not(a) and b) or c) xor d
```
- You have been provided four files in the [archive](challenge-05/boolsforfools_fixed.zip): a.txt, b.txt, c.txt and d.txt, all containing only ones and zeroes
- Use the [script](challenge-05/bool.py) to do the boolean formular for each digit in the files to get the output
- I really needed some time to figure out what to do with the output.. Key is to not have it as a sngle string (cant be decoded to anything) but to keep the format of the input files, so 25 lines with 25 digits each.
- The file assebles an QR code, 1 stands for a black field, 0 is a white field. It can be seen by the corners top/left, top/right and bottom/left
- FLAG: cm18-eJb2-mfTz-pIMu-oKaV

## Challenge 06
**Witchcraft**  
I was just messing around with my magic wand trying out some new spells and all of a sudden the flag was gone.  
All I was left with is the following:  
```
363336643331333832643438363537373432326436383530
333137323264346337393738333932643635373035343465
```
Can you undo my spell and get the flag?
- Hmm, the values look like hex.. Lets try hex to ASCII..
```
636d31382d486577422d685031722d4c7978392d6570544e
```
- Nothing that makes sense.. But still only 0-9 and A-F, hex again?
- Doing hex to ASCII conversion again reveals the flag
- And now I get the hint to *Witchcraft* and the *spell*! They say *hex hex*, so two times hex ;)
- FLAG: cm18-HewB-hP1r-Lyx9-epTN

## Challenge 07
**Happy Eyes**  
Barbara really loves to chat with all friends.  
To express joy they use the characters ^^ which represent eyes of a smiling face.  
Can you find this happy flag?  
Hint: Offset by 2  
```
1xT-Gcm8FV-5cYN-iBc-syHW
```
- First of all, I didnt liked this challenge ;)
- It looks like a transposition because, cm18 is already in the provided data but I could not make sense of it..
- After some hint we found the Rail-Fence-Cipher which is also called ZigZag cipher (I think the ^^ should point you to this) ...
- *Note to myself: If strange ciphered stuff is found, check https://www.dcode.fr/tools-list#cryptography and try all of these obscure ciphers :D*
- Use https://www.dcode.fr/rail-fence-cipher to get all levels and find the flag
- FLAG: cm18-FxVs-T5yc-YHNG-WicB

## Challenge 08
**Lock**  
The key for that lock got lost.  
If you are good at lock picking you will find the flag.
- You have a java class: [Lock.class](challenge-08/Lock.class)
- Use the Java decompiler to get the source code: `jad Lock.class`
- The class combines a key and a cipher in some way..
- As I couldnt get the Java stuff to work easily, i reimplemented the functionality in python, see [lock.oy](challenge-08/lock.py)
- FLAG: cm18-TEl9-sHKR-C01f-xkb8

## Challenge 09
**Meow!**  
Can you lure the cat out of the hiding place?
- A pdf file was given [meow.pdf](challenge-09/meow.pdf)
- Use `pdfimages -all meow.pdf out` to get two images
- First one contains the flag
- FLAG: cm18-cxJg-MB4M-Q9E3-ZYlC

## Challenge 10
**Chest**  
Can you find the flag inside of the chest?
- Check the information about the provided [chest](challenge-10/chest) using `file`:
```
chest: ELF 64-bit LSB shared object, x86-64, version 1 (SYSV), dynamically linked, interpreter /lib64/ld-linux-x86-64.so.2, for GNU/Linux 2.6.32, BuildID[sha1]=5042299eb1d095cd4db443de65640c7073668114, not stripped
```
- Make the file executable (`chmod u+x chest`) and run it:
> This chest is filled with a lot of stuff.
>
> Arrr!
- I tried command line parameters, but output does not change
- After looking at the program using *radare2* and *gdb* it does not look like the program is doing anything.. However, there are many "flags" visible..
- Using `strings chest | grep cm18 > flags` results in 998 flags..
- I tried whether some of them are repeated, but using `unique` still results in the same amount of flags..
- Ok, the end of the flags seem to be used more often.. Lets sort them
```
strings chest | grep cm18 | rev | sort -u | rev
```
- By looking through the flags, you find one that has a unique ending :)
- FLAG: cm18-Ml2l-bUoC-vXXE-Fc8c

## Challenge 11
**Bacon!**  
Get the bacon!
- Download the archive [1000.zip](challenge-11/1000.zip)
- Unpack it to see that it contains a git repository
- Use `git status` inside the repo to find a deleted:
```
	deleted:    0999.zip
```
- Use `git checkout 0999.zip` to restore the archive
- Inside 0999.zip there is another git repository, so I assume we have to unpack 1000 layers of zip -> git -> zip -> git ...
- Lets write a script to do this :)
- First obstacle is in repo 0613.
- Looking at the contents, the repository contains not only one commit, but three. So we have to checkout the right commit to get the right archive.
- Next obstacle is in repo 0045
- The included archive is password protected.. But the password can be found in the commit message!
- Please see the full [script](challenge-11/gitunpack.py)
- The last repository contains a [flag.png](challenge-11/flag.png)
- FLAG: cm18-pCMy-5V56-K0TG-zBWT

## Challenge 12
**On-site Challenge**  
*This challenge was only solvable on site of the Codemash conference and I was not..*

## Challenge 13
**Alice**  
Follow the white rabbit.
- There was nothing given but this sentence.. hmmm..
- The only white rabbit is in the challenge picture.. Maybe?
- Download the image [challenge_13_2433.jpg](challenge-13/challenge_13_2433.jpg)
- Use `file` and `binwalk` to check the file:
```
DECIMAL       HEXADECIMAL     DESCRIPTION
--------------------------------------------------------------------------------
0             0x0             JPEG image data, JFIF standard 1.01
382           0x17E           Copyright string: "Copyright (c) 1998 Hewlett-Packard Company"
155385        0x25EF9         Zip archive data, at least v2.0 to extract, compressed size: 167065, uncompressed size: 167258, name: forest.jpg
322518        0x4EBD6         Zip archive data, at least v2.0 to extract, compressed size: 139956, uncompressed size: 140213, name: meadow.jpg
462542        0x70ECE         Zip archive data, at least v2.0 to extract, compressed size: 171261, uncompressed size: 171490, name: water.jpg
634109        0x9ACFD         End of Zip archive
```
- There is a zip archive hidden in the image!
- Use `binwalk -e challenge_13_2433.jpg` to extract
- You now have three images, lets check them also with `file` and `binwalk`
```
forest.jpg: JPEG image data, Exif standard: [TIFF image data, big-endian, direntries=7, description=This image has a protected secret., xresolution=98, yresolution=106, resolutionunit=2, datetime=2017:12:12 04:01:24], baseline, precision 8, 732x458, frames 3
meadow.jpg: JPEG image data, Exif standard: [TIFF image data, big-endian, direntries=7, description=This meadow is all dried out. Check the water first., xresolution=98, yresolution=106, resolutionunit=2, datetime=2017:12:12 04:00:30], baseline, precision 8, 732x458, frames 3
water.jpg: JPEG image data, Exif standard: [TIFF image data, big-endian, direntries=7, description=steghide was here. With an empty password, xresolution=98, yresolution=106, resolutionunit=2, datetime=2017:12:12 03:58:52], baseline, precision 8, 732x458, frames 3
```
- OK, the forest has a hidden secret, meadow says we have to look at water first and water tells us to use `steghide` (steganography) with an empty password.
- Lets extract the content of water:
```
steghide extract -sf water.jpg -ef water.txt
You search the whole place but you can't find anything.


...


Now get out before you get flushed down.
```
- Lets try the same for the other images.. (empty password does not work for forest)
```
steghide extract -sf meadow.jpg -ef meadow.txt
So you think a mole can speak?!


...


Lucky you, this one can!

He's name is Fred and he tells you the passphrase:

The-Mad-Hatter
```
- Ah, there is the password for the next file!
- Using password "The-Mad-Hatter" for steghide on forest.jpg, you find the flag
- FLAG: cm18-xZl2-eHC5-axW3-ZkZG

## Challenge 14
**Security Regulations**  
Due to some new privacy regulations this flag had to be shred. The classification will be secret or topsecret depending on the content.
- Following the provided link (https://codemash.hacking-lab.com/codemash/secret_challenge_shred1.html) leads to a page containing Shred 1.
```
Shred 1:
Note: the other shreds are classified as «topsecret»!
cm18
```
- Lets try to change the link to https://codemash.hacking-lab.com/codemash/secret_challenge_shred2.html
```
Shred 2:
Note: the contents on this shred were sanitized!
XXXX-XXXX
```
- Ah ok, as the Note in Shred 1 said, the other shreds are *topsecret*
- Use URL https://codemash.hacking-lab.com/codemash/topsecret_challenge_shred2.html
```
Shred 2:
bWh0-VIkC
```
- And https://codemash.hacking-lab.com/codemash/topsecret_challenge_shred3.html
```
Shred 3:
cMf4-72jY
```
- Combine them to get the flag
- FLAG: cm18-bWh0-VIkC-cMf4-72jY

## Challenge 15
**P.A.L.M. Login ™**  
Folks at HOBO Authentication Systems implemented a new authentication system named P.A.L.M. Login  
Prove that you can break it and find a pair of username and passcode to log on.
- Follow the link to [palm.html](challenge-15/palm.html)
- Check the page source to find a checkEntries function:
```
function checkEntries() {
    	  var u = document.getElementById('puser').value;
    	  var p = document.getElementById('ppass').value;
    	  if (u === 'yolo' && p === '1337') {
    		  document.location.href = 'palm_' + u + '_' + p + '.html';
    	  } else {
    		  alert('nope');
    	  }
      }
```
- But trying user = *yolo* and password = *1337* does not work.
- There is another strange JS function:
```
var _0x549b=["value","puser","getElementById","ppass","rolo","length","charAt","href","location","palm_","_",".html","nope"];function checkEntries(){var u=document[_0x549b[2]](_0x549b[1])[_0x549b[0]];var p=document[_0x549b[2]](_0x549b[3])[_0x549b[0]];var ok=false;if(u===_0x549b[4]){if(p>0&&p[_0x549b[5]]==10){ok=true;for(i=0;i<=9;i++){var digit=p[_0x549b[6]](i);if(digit!=9-i){ok=false}}}};if(ok){document[_0x549b[8]][_0x549b[7]]=_0x549b[9]+u+_0x549b[10]+p+_0x549b[11]}else {alert(_0x549b[12])}};
```
- Ah, this one is overwriting the checkEntries function!
- It checks user = *rolo* and password has to be *9876543210*, but again it doesnt work
- There has to be a third function!
- Before searching the HTML again (I was lazy), I used the Browser Console in the Web Developer Tools (but for completeness, it is in the line before `</body>` very far on the right)
- If you run `checkEntries.toString()` in the Console, it gets executed in the context of the page you are currently on and you will directly see the source of the final checkEntries function :)
```
function checkEntries(){var u=document.getElementById('puser').value;var p=document.getElementById('ppass').value;var used=[0,0,0,0,0,0,0,0,0,0];var ok=false;if(u==='cavs'){if(p>0&&p.length==10){ok=true;for(i=1;i<=10;i++){var digit=p.charAt(i-1);var part=p.substring(0,i);if(used[digit]!=0||part%i!=0){ok=false}if(used[digit]==0){used[digit]=1}}}}if(ok){document.location.href='palm_'+u+'_'+p+'.html'}else{alert('nope')}}
```
- What this does is checking the user to be *cavs* and the password has to consist of the numbers 0-9, each number only used once and each substring from length 1-10 has to be dividable by the substring length without a remainder
- You can do brute-force right now, but I made some more assumptions to speed it up..
- Ok, this means the last digit has to be the *0* for sure, otherwise the whole number will not be dividable by 10!
- Every second digit has to be even, otherwise the substrings will not be dividable by even numbers
- Use the brute-force [script](challenge-15/crack.py) to find the password *3816547290*
- Login with user = *cavs* and password = *3816547290*
- FLAG: cm18-zbIc-O4Zh-gmxl-r5J6
