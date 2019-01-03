from PIL import Image
import math
import sys


# Decode QR blocks to ones and zeroes
def qr_to_blocks(pixels, color_index):
	# We assume a 21x21 block QR code here
	bs = int(img.size[0] / 21) # Size of one block
	mid = int(bs / 2) # Middle of the block (half of blocksize)
	blocks = [[0 for x in range(21)] for y in range(21)]
	print("Blocksize={:d}, Mid={:d}".format(bs, mid))

	for x in range(0, 21):
		for y in range(0, 21):
			pixel = pixels[y * bs + mid, x * bs + mid]
			if (pixel == (255, 255, 255)): # white boxes
				blocks[x][y] = 0
			else:
				if (pixel == (0, 0, 0)) or (pixel[color_index] == 0): # black or uncolored boxes
					blocks[x][y] = 1
				else: # full colored boxes (only one color channel)
					blocks[x][y] = 0
			#print("rgb({:d},{:d},{:d}) -> {:d}".format(pixel[0], pixel[1], pixel[2], blocks[x][y]))
	return blocks


# Read out the mask bits in the QR Code
def get_mask(blocks):
	mask = int((blocks[8][4] << 2) | (blocks[8][3] << 1) | blocks[8][2])
	return mask


# Read out the Encoding bits in the QR Code
def get_encoding(blocks):
	encoding = int((blocks[20][20] << 3) | (blocks[20][19] << 2) | (blocks[19][20] << 1) | blocks[19][19])
	return encoding


# Apply the Mask to the blocks
def apply_mask(blocks, mask_ident):
	mask_6 = lambda x,y: (((x * y) % 2) + ((x * y) % 3)) % 2 == 0
	mask_3 = lambda x,y: (x + y) % 3 == 0

	if mask_ident != 3:
		raise RuntimeError("Only Mask Type 3 implemented so far, please add others if necessary.")

	for x in range(0, 21):
		for y in range(0, 21):
			if mask_3(x, y): # If mask is true for this position, flip the bit
				if blocks[x][y] == 1:
					blocks[x][y] = 0
				else:
					blocks[x][y] = 1
	return blocks


# Read one block (8 bits) in upward direction
def decode_block_up(blocks, x, y):
	print("Decoding {:d},{:d} up".format(x, y))
	data = int((blocks[x][y] << 7) | (blocks[x][y-1] << 6) | (blocks[x-1][y] << 5) | (blocks[x-1][y-1] << 4) | (blocks[x-2][y] << 3) | (blocks[x-2][y-1] << 2) | (blocks[x-3][y] << 1) | blocks[x-3][y-1])
	return (data, x-4, y)


# Read one block (8 bits) to the left when coming from a previous upward read
def decode_block_up_left(blocks, x, y):
	print("Decoding {:d},{:d} up/left".format(x, y))
	data = int((blocks[x][y] << 7) | (blocks[x][y-1] << 6) | (blocks[x-1][y] << 5) | (blocks[x-1][y-1] << 4) | (blocks[x-1][y-2] << 3) | (blocks[x-1][y-3] << 2) | (blocks[x][y-2] << 1) | blocks[x][y-3])
	return (data, x+1, y-2)


# Read one block (8 bits) in downward direction
def decode_block_down(blocks, x, y):
	print("Decoding {:d},{:d} down".format(x, y))
	data = int((blocks[x][y] << 7) | (blocks[x][y-1] << 6) | (blocks[x+1][y] << 5) | (blocks[x+1][y-1] << 4) | (blocks[x+2][y] << 3) | (blocks[x+2][y-1] << 2) | (blocks[x+3][y] << 1) | blocks[x+3][y-1])
	return (data, x+4, y)


# Read one block (8 bits) to the left when coming from a previous downward read
def decode_block_down_left(blocks, x, y):
	print("Decoding {:d},{:d} down/left".format(x, y))
	data = int((blocks[x][y] << 7) | (blocks[x][y-1] << 6) | (blocks[x+1][y] << 5) | (blocks[x+1][y-1] << 4) | (blocks[x+1][y-2] << 3) | (blocks[x+1][y-3] << 2) | (blocks[x][y-2] << 1) | blocks[x][y-3])
	return (data, x-1, y-2)


# Get the length information from the QR Code
def get_length(blocks):
	# Length starts at (18, 20)
	(length, x, y) = decode_block_up(blocks, 18, 20)
	return length


# Decode (length) blocks from the QR code
def decode(blocks, length):
	# Starting coords for decoding
	x = 14
	y = 20

	data = []
	up = True
	encoded = 0
	for i in range(2, 13):
		if (i % 3 == 0): # Every three blocks we change to the left
			if up:
				(block, x, y) = decode_block_up_left(blocks, x, y)
				data.append(block)
				up = False # Change read direction
			else:
				(block, x, y) = decode_block_down_left(blocks, x, y)
				data.append(block)
				up = True # Change read direction
		else:
			if up:
				(block, x, y) = decode_block_up(blocks, x, y)
				data.append(block)
			else:
				(block, x, y) = decode_block_down(blocks, x, y)
				data.append(block)
		encoded += 1
		if (encoded == length):
			return data

	# Three blocks in upward direction
	for i in range(0, 3):
		(block, x, y) = decode_block_up(blocks, x, y)
		data.append(block)
		encoded += 1
		if (encoded == length):
			return data

	# Skip one block (sync mask should be there)
	x -= 1
	(block, x, y) = decode_block_up(blocks, x, y)
	data.append(block)
	encoded += 1
	if (encoded == length):
		return data
	(block, x, y) = decode_block_up_left(blocks, x, y)
	data.append(block)
	encoded += 1
	if (encoded == length):
		return data
	(block, x, y) = decode_block_down(blocks, x, y)
	data.append(block)

	return data


# Convert the read numbers to a stream of bits
def data_to_bitstream(data):
	stream = ""
	for d in data:
		stream += "{:08b}".format(d)
	return stream


# Decode all blocks (8 bits) in Byte Encoding (to ASCII)
def byte_decode(data):
	text = ""
	for d in data:
		print("{:x} -> {:s}".format(d, chr(d)))
		text += chr(d)
	return text


# Print the blocks
def print_blocks(blocks):
	for x in blocks:
		line = ""
		for y in x:
			line += "{:d} ".format(y)
		print(line)


# MAIN
if __name__ == "__main__":
	img = Image.open(sys.argv[1])
	pixels = img.load()
	print("Image Size: {:d}x{:d}".format(img.size[0], img.size[1]))

	flag = ""
	for i in range(0, 3): # For all three colors (Red, Green, Blue)
		blocks = qr_to_blocks(pixels, i)
		print_blocks(blocks)

		mask = get_mask(blocks)
		print("Mask: {:d}".format(mask))

		blocks = apply_mask(blocks, mask)
		print_blocks(blocks)

		enc = get_encoding(blocks)
		print("Encoding: {:d}".format(enc))

		length = get_length(blocks)
		print("Content length: {:d}".format(length))

		data = decode(blocks, length)
		stream = data_to_bitstream(data)
		print("Data: {:s}".format(stream))

		text = byte_decode(data)
		print("Text: {:s}".format(text))
		flag += text
	print("Flag: {:s}".format(flag))
