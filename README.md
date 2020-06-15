README.txt
David William Nartey

Program is run from command line/terminal.
Run by first creating the class for the java file.
"javac Enigma.java"
Execute java file with sample run: "java Enigma -i encode -c 0123456789 -k 000 -m yummyramenisanexample"
Takes the form of: "java Enigma" "-i" must be followed by an instruction, either "encode" or "decode". "-c" must be followed by the construction of unique digits from 0 to 9 inclusive. "-k" must be followed by the three character key. "-m" must be followed by the message that we intend to encode or decode. 
The order of the instructions does not matter as long arguments are present.
Program checks for errors in "-i", "-k" and pads for "-m". 
Wrong construction will cause issues. 
Incomplete Decryption Implementation. Permutator for Decryption is functional. See Discussion.txt for more details.