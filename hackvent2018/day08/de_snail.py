from PIL import Image
import sys
from pyzbar.pyzbar import decode


# Decode the image to black/white blocks
def img_to_blocks(pixels):
	# We assume a 25x25 block QR code here
	bs = img.size[0] / 25 # Size of one block
	mid = bs / 2 # Middle of the block (half of blocksize)
	print("Block size: {:f}, Middle: {:f}".format(bs, mid))
	blocks = [[0 for x in range(25)] for y in range(25)]

	for x in range(0, 25):
		for y in range(0, 25):
			pixel = pixels[int(y * bs + mid), int(x * bs + mid)]
			if (pixel == (255, 255, 255)):
				blocks[x][y] = 0
			else:
				blocks[x][y] = 1
	return blocks


# Perform the "de-snail" from inside out
def de_snail(blocks):
	new_blocks = [[0 for x in range(25)] for y in range(25)]
	
	# Start in the middle
	in_x = 12
	in_y = 12
	l = 1
	out_x = 0
	out_y = 1
	direction = "up"
	finished = False

	new_blocks[0][0] = blocks[12][12]
	while (not finished):
		for i in range(0, int(l)):
			# Stop condition
			if ((in_x == 0) and (in_y == 0)):
				finished = True
				break

			# Move reading cursor
			if (direction == "left"):
				in_y -= 1
			elif (direction == "up"):
				in_x -= 1
			elif (direction == "right"):
				in_y += 1
			else:
				in_x += 1
			#print("({:d}, {:d}) -> ({:d}, {:d}): {:d}".format(in_x, in_y, out_x, out_y, blocks[in_x][in_y]))
			new_blocks[out_x][out_y] = blocks[in_x][in_y]

			# Move writing cursor
			out_y += 1
			if (out_y == 25):
				out_x += 1
				out_y = 0
		# Change direction left -> up -> down -> right -> left -> ...
		if (direction == "left"):
			direction = "up"
		elif (direction == "up"):
			direction = "right"
		elif (direction == "right"):
			direction = "down"
		else:
			direction = "left"
		l += 0.5 # Increment by 0.5 to have always two times the same value when casted to int()
	return new_blocks


# Print Blocks
def print_blocks(blocks):
	for x in blocks:
		line = ""
		for y in x:
			line += "{:d} ".format(y)
		print(line)


# Re-size image
def resize(blocks, size):
	new_blocks = [[0 for x in range(25*size)] for y in range(25*size)]

	x = 0
	y = 0
	for line in blocks:
		for pixel in line:
			for i in range(0, size):
				for j in range(0, size):
					new_blocks[x * size + i][y * size + j] = blocks[x][y]
			y += 1
		x += 1
		y = 0
	return new_blocks


def write_img(blocks):
	qr = Image.new("RGB", (25*5, 25*5), (255, 255, 255))
	img = qr.load()

	x = 0
	y = 0

	for line in blocks:
		for pixel in line:
			if (pixel == 1):
				img[y, x] = (0, 0, 0)
			y += 1
		x += 1
		y = 0
	qr.save(sys.argv[1] + "-qr.png")
	return qr


# MAIN
if __name__ == "__main__":
	img = Image.open(sys.argv[1])
	pixels = img.load()
	print("Image Size: {:d}x{:d}".format(img.size[0], img.size[1]))

	blocks = img_to_blocks(pixels)
	print("Input blocks:")
	print_blocks(blocks)	

	print("\n\nDe-Snailed:")
	blocks = de_snail(blocks)
	print_blocks(blocks)

	blocks = resize(blocks, 5)
	qr = write_img(blocks)

	data = decode(qr)
	print(data)
