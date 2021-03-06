# Hacky Easter 2018

## 01 - Prison Break
- Search the text on the origami to find the "Prison Break Code"
- The dots are numbers, so we have 1334322 and 3342321
- Write the telephone numbers over the converted dots
- The telephone numbers are the buttons on the numpad and the dots say which letter on that button you should take
- So 7-1 means numpad button 7 (PQRS), 1st letter: P

7747663  7475464
1334322  3342321
prisone  risking


## 02 - Babylon
- The babylon.txt does not look like any known encoding
- The text says that there is not only the tower in Babylon with walls and shelves..
- What elso was there, publically known, in Babylon? The library (which has many shelves)
- If you search for "babylon library", you find several results, https://libraryofbabel.info is what we search
- Enter the strange string from babylon.txt in "Browse" in the field on top
- Then use the number combination - Wall: 4, Shelf: 4, Volume: 28, Page 355


## 03 - Pony Coder
- The first idea was that this is punycode (from the name)
- Try different changes to the displayed string, e.g. replacing gn or tn with xn and use -- but it didnt work
- There is a little more work required, the correct punycode is (after several tries): xn--gn-tn-gha87be4e
- Using a punycode converter (https://www.punycoder.com/) this results in gìn-tônì©
- The challenge says "Lowercase and spaces only, and special characters!"
  But special characters do not include strange stuff ;)
- Correct password is gin tonic


## 04 - Memeory
- When you check the page source, the single pieces were ordered
- You can see the images when you use the Inspector in FF WebDeveloper
- The a href link tell you what matches (#card_0 and #card_1 have the same image and so on)
- When you move the Inspector to a new window and zoom out the memory so you see all cards, then
  - you can search for #card_0, see which one gets highlighted and click it
  - search for #card_1, see the highlight and click that one
  - go on until you have all matching cards :)
- Scripting alternative:
  - The cards are ordered at the beginning and so are the class names #legespiel_card_0, #legespiel_card_1
  - Execute from the Web Console (FF): for (var i = 0; i < 100; i++) { $("#legespiel_card_" + i + " .boxBack").click(); }
  - Done :)


## 05 - Sloppy & Paste
- In App Challenge (iPhone)
- You see a text field containing some data (looks base 64 encoded)
- You cannot select or copy the data from the field
- There is a button to copy everything to clipboard
- I sent myself an email with the data
- Base64 decode it to get a PNG image
- The image shows an easter egg but without QR code, there is the text "Copy Operation Failed :("
- If you compare the beginning of the b64 data with the text field, it is the same
- If you scroll to the end of the text field, the end differs..
- I tried the same on my Samsung but same effect, I cannot copy the text directly
- Lets download the APK (I dont know how to get the iphone app) from apkpure.com
- Unzip with 7-zip
- In assets/www/challenge05.html there is the screen and you can copy the whole text from the text field
- Use a small script to base64 decode the data and write it as binary
- You get a PNG with the QR code


## 06 - Cooking for Hackers
- The recipe shows strange ingredients..
- They look base64 encoded, so lets decode them
- "salt", "oil", "t7w2g", "ntdo.", "onion"
- The first two and the last one look good but the others?
- I tried to search for a recipe with the same ingredients but no luck
- Tried different encodings for the two strange ones, but didnt succeed
- Then i looked again at what i had and there is ".onion" at the end
- Its a TOR address!


## 07 - Jigsaw


## 08 - Disco Egg
- The egg QR code is flashing with many differen colors..
- When you check the page source, the QR code area is a table
- Each td element has different color possibilities inside the "class" attribute
- Remove every color but white and black
- Remove the script tag at the beginning so the flashing stops
- Add a CSS rule .black { background-color: black; }
- Open the HTML file locally in the browser to see the QR code


## 09 - Dial Trial
- In-App Challenge (iPhone)
- When pushing the Dial button, you hear some dial sounds (you need to unmute the phone!)
- The audio file can be obtained from the APK in res/raw/dial.mp3 (or search for some audio formats \*.mp3 / \*.wav in the whole APK)
- I used http://dialabc.com/sound/detect/ to analyze the audio file (you need to convert the mp3 to wav first)
  4*7#2*6#1*2#2*5#2*3#3*6#2*6#2*6#3*6#2*5#3*4#1*2
- This is not the correct password, also only the numbers dont work
- Lets try tu multiply the blocks, but this also doesnt work
- Hmm, what else?
- In the first challenge, we also had combinations of two numbers
- In this case, the second one tells you the numpad button and the first one how often to press
  snakeonnokia


## 10 - Level Two
- Download the game and install the runtime if you dont have it
- Start the game and try to explore the world
  -> Doesnt work, you cannot get from the "prison" island..
- Save the game and exit
- Open the file in save editor and edit the values (like in teaser), max HP, all items, ...
  (https://f95zone.com/threads/rpg-maker-save-editors.51/)
- Start again
  -> Still the same problem. But you have an egg in the inventory with a hex string:
     (1): 7034353577307264355f3472335f6330306c: p455w0rd5_4r3_c00l
  -> This doesnt work as password ;)
- Ok, to get from the island i saved the game, moved one step, saved again (different slot) and tried to compare the saves
  -> Unfortunately I didnt find the place where the position is stored..
- Next try, open the file in the RPG Maker VX Ace editor
  (http://www.rpgmakerweb.com/download/free-trials/trial-rpg-maker-vx-ace)
- Download and use a decryptor for RPG Maker
  (https://wiki.rpgmaker.es/ayuda/utilidades/rpg-maker-xp-vx-vx-ace-decrypter)
  If you search for other tools, make sure there is no malware in them (happens often..!)
- After decrypting, you cannot open the files directy in editor :(
- Create a new empty project with the editor, save it somewhere & close editor
- Overwrite the project files with the decrypted ones from the hacky easter RPG
- Open the project again and you will see the maps :)
- You could not edit the landscape so you can escape the prison and explore the game but this will take time..
- Swith to "events" view (F6) in the editor
- Take a look at the event regions where no enemy is in there (like the two tombstones on the way from the prison)
- They contain similar hex strings:
  - In front of tombstone in prison: (2): 7034353577307264355f052d066b15035433: p455w0rd5_-kT3
  - In garden (top/middle) in front of a painting on the hedge: (3): 70343535773072105d6c6b05032d0f546f4c: p455w0r]lk-ToL
  - In Dimension Rift (bottom/left) beneath the window: (4): 7034353577307264355f3406033b5749114c: p455w0rd5_4;WIL
- There is also a message on the tombstone (prison) that it all makes sense at the end
- The last map is Heaven and if you take a look at the bottom of the map there are some signs
  - I would guess this means 33 ^ 6C so some XOR with HEX !?
- I wrote a small script that XORs all the hex strings with each other in every combination and there are some that make sense:
  - (1) ^ (2): 00000000000000000000315f35347633645f - 1_54v3d_ (I saved)
  - (1) ^ (3): 000000000000007468335f7730726c645f20 - th3_w0rld_ (the world)
  - (1) ^ (4): 000000000000000000000074306434792120 - t0d4y! (today!)
  - (2) ^ (3): 000000000000007468336e2805461a573b7f - th3n(FW; (then)
  - (4) ^ (3): 000000000000007468335f030016581d7e00 - th3_X~ (the)
  - (1) ^ (2) ^ (3) ^ (4): 000000000000007468336e5c35222e2e1a5f - th3n\5".._ (then)
- Try some combinations to get I saved the world today! (1_54v3d_th3_w0rld_t0d4y!)
  This means XOR every found string with the one from the egg item



## 11 - De Egg you must
- Download the zip and try to extract it (needs password)
- Ok, where do we get the password?
- I tried to analyze the zip itself and also the image from the challenge but didnt find anything
- Next, bruteforce
- Use zip2john basket.zip > basket.hash to generate a file suitable for John the Ripper
- Then use john --show basket.hash to crack it (You need to jumbo version for zip and I needed to downgrade to 1.7.9 or it didnt work!)
- Easy, the password is thumper
- After extractring, you get 6 files, egg1 to egg6
- Check all of them with file and binwalk.
  - Only the first one seems to be an mp4 file
- Try to playback, but didnt work (corrupt file)
- Take a look at the file sizes, egg1 to egg5 are exactly the same size
  - This looks like the file was split to a specific number of bytes!
  - Lets put them back together: cat egg1 egg2 egg3 egg4 egg5 egg6 > egg
- Now you can play the mp4 and have the keyboard cat (original song, but other cat) and some eggs falling down in the background
- I extracted all the single frames from the video part to see if the egg is directly in the video
  ffmpeg -i egg -q:v 2 frame%4d.jpg
- Nothing found..
- ffprobe also doesn show much..



## 12 - Patience
- In-App Challenge
- The app counts down from 100000 very slowly and you cannot speed it up (change time, ...)
- Lets look what it does in the APK: assets/www/challenge12.html
- It seems to generate a hash
- The first input is "genesis" and the count of 100000
- This is repeated 100000 times every 3 seconds..
- With a small script we do the same and generate different hashes (md5, sha-1, sha-256, ripemd) in a loop
- Afterwards verify whether the link exists
- SHA-1 is the right one
- TODO: Add final hash


## 14 - Same same...
- You need to upload two files that can be parsed to a QR code with different text inside but the same SHA-1 hash.
- When you check the provided upload page, the QR codes need to contain the text "Hackvent" and "Hacky Easter"
- The SHA-1 hash must match
- They use the QrReader to read the QR codes and parse the text
- Lets take a look at the QrReader: https://github.com/khanamiryan/php-qrcode-detector-decoder
- If you check the source of lib/QrReader.php, function `__construct`
- They use Imagick if available to parse the files
- Imagick can parse JPEG out of a PDF!
- So we can use two QR code JPEGs, put them in a PDF and use the SHAttered attack to produce two PDF with same SHA-1 hashes.
- I generated two PDF QR codes from: https://qrd.by/qr-code-generator-pdf
- Then used the python script from https://github.com/nneonneo/sha1collider
- However, only one PDF was intact afterwards, the other one did have the same SHA-1 but the QR code was not readable anymore :(
- There is a flag "--progressive" that does not mess with the JPEG spec
- Afterward it worked :)


## 15 - Manila greetings
- You get a string with 5 words with 5 letters each and a last one
- Additionally there is a deck of cards?
- I searched for "encryption card deck" and directly found Bruce Schneiers Solitaire Cipher
- As I was too lazy to implement it myself, the website here helps: https://ermarian.net/services/encryption/solitaire
- Enter the encrypted text in the box above
- Then we have to perform some things to the deck. The website expects the values differently..
  1. Replace ([dsch])10 with T$1
  2. Swap the numbers and letters ([dsch])([0-9]) with $2$1
  3. Replace card names with uppercase letters ([kqja]) with \U\L$1
  4. Replace jokers j([ab]) with \U\L$1
  5. Put everything in a single string by replacing the line endings ([\r\n]\*) with space
- Now you can use this as the "Key" but be sure to select "Cards" from the checkboxes
- Decrypt to get: THEPA SSWOR DISCR YPTON OMICO N
- Password is CRYPTONOMICON :)


## 16 - Git
- Download and unzip archive
- Check git log and try different commit IDs
- Check git branch and try also the other branch
- The found QR code eggs do not work :(
- Search for unreferenced files using git fsck --unreachable
  There is one blob! ID: dbab6618f6dc00a18b4195fb1bec5353c51b256f
- Use git show dbab6618f6dc00a18b4195fb1bec5353c51b256f > egg to get the contents
- You have the QR code egg :)


## 17 - Space Invaders
- The hint already points you to the site used for "encryption" (codemoji.org)
- You can encrypt/decrypt messages there with an emoji key
- Its a litte complicated to get your message on the site
- Lets first create a new message (whatever) and encrypt it with any smiley
  Use "Share this message" and then copy the link
- If you look at the Network tab of FF Web Developer Tools and visit the "share" link, you see some redirects
  https://mzl.la/2aleeX6
  https://codemoji.org/?data=eyJtZXNzYWdlIjoi8J%2BYtfCfk6Twn4%2BD8J%2BYtSIsImtleSI6IvCfmIEifQ%3D%3D#/landing
- The data part looks interesting
- It is base64 encoded and URL encoded, so revert this to get a JSON
  {"message":"😵📤🏃😵","key":"😁"}
- Now exchange the message with what we have in the message.txt
- And for the key we now have to pick the right emoji, so you can write a script that tries all of them or first try some interesting one
- There is e.g. one with the Space Invaders icon: 👾
  JSON looks like: {"message":"⚾⭐📯💵🎨📢📘💪☀🌆💪🐸🎨🐦📢","key":"👾"}
- Then base64 encode and afterwards URL encode to get
  https://learning.mozilla.org/codemoji/share.html?data=eyJtZXNzYWdlIjoi4pq%2B4q2Q8J%2BTr%2FCfkrXwn46o8J%2BTovCfk5jwn5Kq4piA8J%2BMhvCfkqrwn5C48J%2BOqPCfkKbwn5OiIiwia2V5Ijoi8J%2BRviJ9
- Now select the Space Invaders emoji and get the password: invad3rsmustd13


## 25 - Hidden Egg #1
- The hint "Heads up" brought two ideas to my mind
  1. Its in the HTML <head> tag - No it wasnt :(
  2. Its in the HTTP Header
- When you check the HTTP Header, there is an unknown field Content-Eggcoding
  (If you dont see it, be sure to force reload the page (no cache), if you get a 304 than the header value is not there)
- Value of it is: aHR0cHM6Ly9oYWNreWVhc3Rlci5oYWNraW5nLWxhYi5jb20vaGFja3llYXN0ZXIvaW1hZ2VzL2VnZ3MvYmEwYzc0ZWQ0MzlhYjQ3OTVmYzM2OTk5ZjU0MmJhNTBiMzI2ZTEwOS5wbmc=
- Base64 decode this to get: https://hackyeaster.hacking-lab.com/hackyeaster/images/eggs/ba0c74ed439ab4795fc36999f542ba50b326e109.png
- There you will find the egg QR code


## 26 - Hidden Egg #2
- The hint "This egg is hidden in a very subtile manner. Perhaps you need to browse on the edge."
- Lets look at the page in Microsoft edge
- Doesnt change anything.. favicon is the same, source code, ...
- But "tile" is written in italic in the hint..
- We can add websites as "tiles" to the Win10 star menu!
- After changing the size to big, the QR code is shown
- You can also check the config file under https://hackyeaster.hacking-lab.com/browserconfig.xml
- There you find all images and theb 310x310 one is the egg :)


27 - Hidden Egg #3
- Hint: "Got appetite? What about an egg for launch?"
- So it will be somewhere in the app.
- At first I checked the app stores because of the "launch"
- So lets take a look at the APK
- In the assets/www folder there are only the "background" eggs without QR code
- If you search the whole APK for *.png and scan through, you find jc_launcher.png under res/drawable
