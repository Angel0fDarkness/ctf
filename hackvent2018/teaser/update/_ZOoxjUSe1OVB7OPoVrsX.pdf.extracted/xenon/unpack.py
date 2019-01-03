import subprocess
import sys

#password = "Far shed each high read are men over day. Afraid we praise lively he suffer family estate is. Ample order up in of in ready. Timed blind had now those ought set often which. Or snug dull he show more true wish. No at many deny away miss evil. On in so indeed spirit an mother. Amounted old strictly but marianne admitted. People former is remove remain as. Sudden looked elinor off gay estate nor silent. Son read such next see the rest two. Was use extent old entire sussex. Your nugget ist HV18"

password = ". All men drew its post knew. Of talking of calling however civilly wishing resolve. Eat imagine you chiefly few end ferrars compass. Be visitor females am ferrars inquiry. Latter law remark two lively thrown. Spot set they know rest its. Raptures law diverted believed jennings consider children the see. Had invited beloved carried the colonel. Occasional principles discretion it as he unpleasing boisterous. She bed sing dear now son half. Congratulations, you have reached the last level."

for c in password:
	subprocess.call(["unzip", "-P", c, "-d", ".", "-o", "z.zip"])

print "Finished"
