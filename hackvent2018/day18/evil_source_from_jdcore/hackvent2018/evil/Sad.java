package hackvent2018.evil;

public class Sad {
  public static byte[] b = {
    -119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 1, 44, 
    0, 0, 1, 44, 8, 3, 0, 0, 0, 78, -93, 126, 71, 0, 0, 2, -3, 80, 76, 84, 
    69, 0, 1, 0, 4, 1, 6, 0, 3, 6, 3, 6, 2, 6, 7, 18, 7, 10, 6, 11, 
    10, 0, 11, 9, 14, 13, 16, 12, 15, 16, 24, 18, 17, 7, 18, 16, 20, 22, 19, 2, 
    18, 21, 3, 26, 23, 9, 24, 23, 15, 22, 23, 30, 23, 24, 22, 25, 24, 27, 26, 27, 
    6, 25, 27, 14, 27, 28, 35, 30, 28, 32, 32, 30, 21, 33, 32, 8, 31, 32, 15, 30, 
    31, 29, 35, 36, 19, 34, 34, 42, 36, 37, 14, 37, 35, 38, 35, 37, 35, 40, 38, 27, 
    41, 40, 13, 40, 40, 19, 40, 39, 33, 39, 41, 24, 43, 44, 22, 42, 43, 41, 43, 44, 
    32, 47, 45, 12, 44, 45, 28, 47, 46, 19, 43, 44, 51, 45, 44, 47, 47, 47, 25, 51, 
    50, 23, 49, 51, 33, 51, 50, 38, 51, 51, 29, 51, 49, 52, 49, 50, 48, 52, 51, 44, 
    55, 53, 20, 50, 51, 59, 55, 54, 27, 57, 58, 19, 59, 57, 30, 59, 56, 44, 56, 59, 
    24, 55, 57, 54, 56, 56, 64, 53, 58, 60, 59, 59, 37, 58, 58, 50, 59, 57, 60, 63, 
    64, 24, 66, 63, 30, 65, 63, 35, 61, 63, 60, 64, 64, 51, 64, 66, 32, 64, 63, 66, 
    66, 66, 43, 63, 64, 72, 66, 66, 58, 66, 67, 49, 65, 67, 64, 69, 70, 30, 72, 72, 
    26, 69, 72, 37, 73, 71, 42, 72, 71, 63, 72, 70, 73, 68, 72, 74, 70, 72, 69, 70, 
    71, 80, 74, 75, 34, 78, 79, 38, 75, 77, 74, 81, 80, 34, 82, 80, 51, 77, 78, 86, 
    81, 79, 66, 80, 78, 81, 80, 79, 72, 80, 83, 47, 82, 83, 42, 77, 81, 83, 80, 82, 
    79, 86, 87, 45, 88, 87, 40, 90, 88, 35, 82, 86, 88, 84, 86, 83, 90, 90, 49, 89, 
    87, 91, 92, 91, 44, 90, 89, 81, 95, 94, 47, 93, 92, 84, 94, 91, 95, 91, 93, 90, 
    99, 97, 44, 98, 97, 50, 93, 94, 102, 91, 95, 98, 95, 97, 94, 98, 96, 100, 102, 101, 
    53, 104, 102, 48, 102, 105, 50, 97, 102, 104, 100, 102, 99, 102, 101, 105, 108, 105, 51, 110, 
    108, 54, 108, 110, 48, 109, 112, 56, 106, 108, 105, 109, 107, 111, 107, 108, 117, 106, 111, 113, 
    112, 115, 59, 114, 116, 54, 113, 111, 115, 118, 118, 50, 117, 119, 57, 114, 116, 113, 117, 115, 
    119, 114, 119, 121, 123, 123, 55, 122, 124, 62, 119, 124, 126, Byte.MAX_VALUE, Byte.MIN_VALUE, 66, 122, 124, 121, 125, 
    123, Byte.MAX_VALUE, -127, -127, 61, 126, Byte.MIN_VALUE, 125, -120, -122, 59, -121, -121, 66, -127, -125, Byte.MIN_VALUE, -121, -124, -120, 
    -126, -121, -119, -116, -117, 70, -122, -120, -123, -113, -115, 66, -115, -117, -113, -120, -115, -112, -108, -110, 
    70, -116, -114, -117, -110, -112, -108, -104, -106, 74, -101, -105, 69, -104, -102, 70, -100, -102, 78, -104, 
    -107, -103, -109, -104, -101, -101, -98, 80, -105, -103, -106, -97, -95, 77, -96, -99, -95, -99, -97, -100, 
    -101, -96, -94, -92, -89, 82, -87, -86, 78, -87, -84, 87, -92, -90, -93, -94, -89, -86, -88, -90, 
    -86, -82, -81, 83, -78, -78, 86, -81, -84, -79, -86, -81, -79, -83, -81, -85, -75, -74, 89, -71, 
    -71, 93, -67, -68, 89, -79, -74, -71, -72, -75, -70, -67, -67, 96, -73, -71, -75, -62, -63, 93, 
    -66, -68, -64, -69, -64, -61, -66, -64, -68, -56, -57, 99, -61, -63, -59, -51, -53, 103, -56, -58, 
    -54, -47, -50, 99, -60, -55, -53, -49, -46, 101, -55, -53, -56, -109, -40, -17, -46, -43, 104, -102, 
    -39, -22, -96, -37, -27, -43, -40, 107, -47, -49, -45, -52, -47, -44, -49, -46, -50, -92, -34, -36, 
    -39, -36, 111, -83, -31, -42, -34, -33, 107, -76, -30, -49, -41, -39, -42, -37, -40, -36, -43, -37, 
    -35, -67, -27, -58, -29, -28, 111, -63, -24, -64, -26, -24, 115, -37, -31, -29, -56, -21, -71, -33, 
    -31, -34, -22, -21, 118, -49, -19, -77, -20, -19, 120, -31, -26, -24, -28, -26, -29, -16, -16, 116, 
    -36, -15, -91, -13, -14, 118, -27, -21, -19, -29, -12, -98, -23, -21, -24, -25, -10, -103, -9, -10, 
    122, -21, -8, -108, -17, -6, -112, -5, -6, 125, -16, -14, -17, -14, -4, -117, -19, -13, -11, -3, 
    -4, Byte.MAX_VALUE, -10, -2, -122, -1, -2, 122, -1, -2, -127, -1, -1, -125, -11, -9, -13, -1, -1, -119, 
    -8, -5, -9, -2, -1, -4, -37, -38, -3, -8, 0, 0, 0, 1, 98, 75, 71, 68, 0, -120, 
    5, 29, 72, 0, 0, 0, 9, 112, 72, 89, 115, 0, 0, 29, -121, 0, 0, 29, -121, 1, 
    -113, -27, -15, 101, 0, 0, 0, 7, 116, 73, 77, 69, 7, -30, 9, 19, 18, 23, 27, 72, 
    -96, -80, 77, 0, 0, 21, 96, 73, 68, 65, 84, 120, -38, -19, -35, 123, 88, 84, -43, -38, 
    0, -16, -55, 57, -124, -95, 7, 79, 68, 38, -110, -95, 24, 117, 52, -95, -68, 20, 81, 120, 
    -44, -46, -58, 46, 20, 106, 6, 114, -52, 52, -95, 44, 34, -17, 102, 41, -95, -106, 26, 114, 
    -60, 11, -118, -120, 122, 70, 41, -87, 32, 39, 11, -121, 2, 83, 70, 27, 112, -68, 96, -109, 
    70, 115, 56, 124, -45, -16, 73, -24, 52, -58, -73, -49, 25, -110, -10, -13, -50, -52, 122, -66, 
    -31, -110, -96, -52, 48, 123, -42, -66, -114, 123, -65, 79, Byte.MAX_VALUE, -16, -104, 110, -42, -6, -19, -75, 
    -42, 94, -73, -67, -74, 12, 73, 65, 57, 100, 18, -127, -124, 37, 97, 73, 88, 18, -106, -124, 
    37, 97, 73, 4, 18, -106, -124, 37, 97, 73, 88, 18, -106, -124, 37, 17, 72, 88, 18, -106, 
    -124, 37, 97, 73, 88, 18, -106, 68, 32, 97, 73, 88, 18, -106, -124, -27, 93, -40, -102, 47, 
    -42, -41, 29, 43, -34, -69, 97, -11, -44, -87, 83, 31, 9, -18, -120, 7, -90, 78, 125, 122, 
    -53, -122, 93, -59, -57, -21, -22, 47, 94, -79, 73, 88, 8, 57, -50, 21, 111, -99, -7, -48, 
    -45, Byte.MAX_VALUE, Byte.MAX_VALUE, -19, -19, -115, 74, -107, 90, -83, 59, -91, -85, 49, 119, -124, -31, -44, -87, 83, 
    101, -22, 34, -27, 7, 11, 95, 123, -14, -23, -121, 102, -82, 41, 57, 33, 102, 44, 91, -59, 
    -5, -3, 123, 5, -89, -26, 107, 1, 72, -46, -7, -97, -69, 0, 18, 0, -76, 5, 115, 66, 
    100, 97, 43, 42, 108, -30, -61, 106, -86, 44, 92, -14, -52, -125, 73, -69, 53, 22, 59, 64, 
    15, 78, 93, -60, 0, -20, -106, -61, -71, 73, Byte.MAX_VALUE, 125, 98, 69, 97, 101, -109, 120, -80, -102, 
    -10, -116, 13, 122, 45, 95, 103, 36, 40, 58, 93, 35, 70, 24, -85, 63, 122, -83, -49, -40, 
    61, -105, 68, Byte.MIN_VALUE, 117, -91, -2, -45, -15, 55, 39, 29, -10, -102, -23, 58, -78, -61, 41, 55, 
    -113, -1, -72, -34, 118, 67, 99, -99, 120, 47, 46, 121, -69, -90, -123, -114, -44, 31, 94, 45, 
    -38, -19, -55, 113, 107, -114, -33, -88, 88, -114, -67, 125, 66, -44, 68, 11, -55, 88, -76, 16, 
    7, 67, -3, 119, -34, Byte.MIN_VALUE, 88, -105, -113, 44, 29, -80, -80, -38, 14, 36, -93, 1, 118, 93, 
    -58, -112, 5, 71, -102, 110, 40, -84, 11, 43, -5, -81, -46, -104, -127, 97, -86, -10, -6, 104, 
    -42, -82, -22, 63, -65, -23, 70, -63, 114, 92, 90, 32, -49, 38, 88, Byte.MIN_VALUE, -70, 10, 70, 100, 
    -53, 87, -41, 59, 110, 4, -84, -110, 89, 10, 37, 1, 44, 90, -75, 22, 47, -117, 50, 126, 
    102, -119, -49, 99, 125, 27, 52, -15, -108, -107, -28, 32, 90, 116, 49, -3, -65, -11, 101, 44, 
    -37, -79, -23, 15, 106, -20, 64, 114, 18, -50, -50, -41, -125, -45, 43, 109, -66, -118, 85, 49, 
    34, 85, 7, 28, 81, -75, -113, 33, -75, 41, -9, 29, -9, 73, -84, 75, 113, -63, -27, 28, 
    74, 117, 120, 105, -6, 78, -81, -9, 57, -84, -90, 53, 35, -117, Byte.MIN_VALUE, 115, -85, -42, -54, 88, 
    48, 50, -21, -78, 111, 97, 125, -20, -81, 36, 72, -98, -62, -102, -21, Byte.MAX_VALUE, -64, -121, -80, -50, 
    -59, 62, 106, 4, -66, -84, -100, -123, -53, -12, 104, -36, 57, 95, -63, 90, 121, -65, 26, -8, 
    -77, 106, 107, -23, 15, -33, -65, -58, 39, -80, -22, -125, -106, -13, 42, -43, -31, -107, 60, -80, 
    86, -16, 88, -51, -17, 13, -41, 8, -64, -118, 36, -19, -27, 81, -21, -101, -123, -115, 85, -37, 
    63, -97, 16, -124, 85, -21, -120, 113, 119, -1, 90, 1, 99, 57, 14, 4, -108, 11, -124, -86, 
    -115, -85, -68, 111, -79, 67, -88, 88, 77, -45, 82, 76, 2, -78, 114, 106, 25, 83, -89, 53, 
    11, 19, -21, -76, -65, 82, 80, 84, 109, -95, -108, -99, 22, 34, 86, 97, -56, 41, 16, 30, 
    22, 104, 67, 11, -123, -121, 53, 75, 65, 8, -113, -86, 85, -53, 28, 51, 77, 96, 88, -51, 
    -93, 51, 64, -112, 86, -83, -99, -120, -123, -109, 46, 10, 9, -21, -36, 64, -107, 80, -87, 90, 
    -121, 63, -86, -96, -45, -62, -63, 58, -17, -89, 35, -123, 28, -96, -21, 125, 82, 40, 88, -123, 
    -125, 116, 64, 10, 91, 75, 31, 81, 40, 12, -84, -11, -118, 6, -127, 91, -75, 54, -13, -47, 
    91, -123, Byte.MIN_VALUE, -11, -50, -93, 86, -66, 4, 26, 27, -67, -104, -120, 120, 120, 19, -1, 88, -53, 
    -46, 120, 123, 12, -38, -109, -125, -67, 24, -76, 91, -109, 22, -13, -115, -75, 52, -123, -73, 25, 
    81, -46, -24, 39, -109, 121, 51, 29, -76, 112, 61, -65, 88, -53, -98, -27, -81, -71, 106, -119, 
    -105, 57, 35, 82, 75, 57, 5, -42, -25, -42, -16, -119, -75, 108, 121, 11, 111, 88, -96, -108, 
    -75, -123, -33, 114, -54, -1, -94, 101, -7, 98, -2, -80, -106, 60, -58, -29, 76, 123, -107, -20, 
    -113, 8, 49, 80, 78, 70, -46, 38, -66, -80, 120, 108, -37, 73, -78, 49, 88, -42, 25, -101, 
    -87, 38, -60, -102, -76, -110, 31, -84, -49, -98, -91, -45, -74, -45, 116, -122, 69, -78, -82, 65, 
    -71, 112, -127, -94, -112, 15, -84, 125, 3, 26, -15, -77, 74, -108, -57, 107, 104, 89, 21, -55, 
    -82, 11, 37, -75, 73, 15, -80, 12, 57, -64, 61, -42, -111, 104, 11, 110, -39, 0, 123, 89, 
    -76, 76, -106, 64, 103, 11, -82, 86, 126, 61, -106, 44, -86, -118, -94, 86, 72, 9, -41, 88, 
    77, 1, 6, -20, 71, -66, 49, -86, 45, 119, 52, -90, -96, -95, 58, 68, -42, 61, 10, -88, 
    93, -48, 60, -8, 2, -73, 88, -11, 119, 96, -82, 76, 0, 84, 45, -22, 40, 21, 41, -76, 
    118, 119, -25, -6, 117, -41, 82, 104, 41, -75, -124, -27, -73, 54, 115, -119, 101, 27, -95, -58, 
    -76, 34, 82, -81, 86, -96, -34, 70, 90, 77, 124, 67, 100, 119, 45, 121, -66, -99, 82, -125, 
    119, 123, 51, -121, 88, -17, -28, 96, 89, -127, -87, -96, 107, -50, 10, 104, -10, -76, 54, 7, 
    118, -61, -110, 41, -12, 20, 82, 6, 57, -53, -72, -61, -38, -6, 44, 94, -43, -55, -66, -74, 
    -91, -119, -89, 57, -84, -124, -102, -28, -18, -123, 43, Byte.MIN_VALUE, -54, 125, 108, 73, -36, -55, 21, -42, 
    -15, -69, -79, -78, -90, -22, -42, -54, -104, 105, 119, -29, -9, -69, 104, -25, -93, 45, -98, -1, 
    -95, 37, -32, 56, 55, 88, -51, 67, -67, -97, 24, 5, -21, 65, 69, -9, 108, -47, -33, 65, 
    2, 6, 23, -123, -53, 79, 73, 122, -70, 48, -100, 26, 122, -103, 19, -84, -69, -68, 95, 75, 
    -123, 83, -111, 114, 23, 101, 64, 102, -95, 63, 92, 2, -115, -117, -57, 98, -108, -57, 110, 9, 
    20, 77, -30, 2, 43, 43, -43, -18, 101, 118, 72, 67, -94, -52, 117, 100, 51, 48, -74, 4, 
    34, -66, -5, -115, -112, 31, -76, 122, -72, 52, 60, -101, -59, 62, -42, -71, -119, 94, 54, -53, 
    -96, 75, -23, -19, -58, 74, 54, -56, 66, 50, -96, 5, -22, -16, -18, -105, 78, -84, 1, 15, 
    13, 67, -24, 49, -42, -79, -122, -106, 123, -103, -105, 85, 114, 119, 84, -78, 20, 53, 19, 88, 
    -83, -109, 9, -82, 90, -82, -125, -98, 70, 1, -79, 14, -106, -79, 102, -83, -13, -90, -26, 56, 
    59, 86, 110, -87, 2, 82, -11, -52, -51, -16, -40, 15, -71, 40, 92, -55, 30, 10, 87, -58, 
    59, -20, 98, -107, 62, 14, -34, -44, -113, -51, -3, -36, -106, -86, 85, 6, -110, -55, -55, 48, 
    -80, 108, 118, 113, 63, 10, 122, 108, 94, 33, -28, 0, -101, 88, -74, -15, 6, 47, 26, -34, 
    -93, 33, 110, 75, 85, -102, -103, -15, 105, 67, -88, 10, -19, -2, -117, -30, 123, 122, 44, -126, 
    -15, -82, 38, 22, -79, 22, 124, 0, -108, 75, -107, -85, -114, 85, 7, 85, 110, 13, 43, -81, 
    30, 90, 114, -70, -1, -82, -48, -3, 61, 105, -19, -34, -64, 30, 86, 69, 0, 101, 43, 99, 
    -88, -37, 10, -104, -51, -34, -94, -84, -95, 123, 89, -106, 39, -12, -16, -12, -74, -36, 118, -98, 
    45, 44, -37, 61, 122, -118, 71, 48, -24, -45, -36, 73, -35, -10, 46, -101, 107, -3, -50, 86, 
    -78, 123, 23, 53, 80, -35, 67, -113, 54, -56, -63, 18, 86, 94, 38, 53, 43, -29, -100, 
    0, 119, 86, -7, 70, -106, -41, 56, 64, -81, 112, 81, -72, -36, -2, 82, -56, -50, 98, 7, -85, 
    -71, -81, -119, -46, 6, -124, 124, -73, 109, -43, -69, 36, 7, -85, 65, -112, -21, -30, 86, 29, 
    117, -9, -73, -119, -5, -101, 88, -63, 26, -21, 121, -38, 22, Byte.MIN_VALUE, -56, 15, 118, -41, 91, 87, 
    114, -76, -39, 6, -86, 93, -116, -82, 82, -36, 61, 126, -107, 115, -39, -64, 42, -115, -12, -100, 
    87, 80, -6, -71, -23, -124, -54, -117, -72, 92, Byte.MIN_VALUE, 45, -22, -98, Byte.MIN_VALUE, -34, 110, 90, 46, -24, 
    -5, 53, 11, 88, -45, -12, 30, -89, 97, -54, -94, -36, -108, -86, -16, -51, -36, 46, -57, -126, 
    -15, -6, -62, 37, -113, -81, 113, 55, 116, 29, -31, 96, 28, -85, 34, -55, -45, 48, -66, 60, 
    -58, 93, 91, -91, -30, 124, -73, 27, 88, -53, -81, 105, -71, 66, 52, 110, 55, 101, -64, -14, 
    82, -58, -79, -4, 122, -34, -83, 2, -90, 104, 55, -11, 47, 32, -97, -97, 69, 126, 75, -25, 
    -52, -115, -4, -35, -98, -110, 80, 61, -108, 105, -84, -84, -123, 61, -49, 98, -103, -35, 12, 109, 
    -94, -107, 102, -98, -10, 67, 56, -57, 16, 29, -125, -21, -8, -98, -105, 95, 33, 101, 39, -77, 
    88, 77, 97, 30, -101, 118, 87, 84, -95, 26, -66, -74, 80, -74, -9, 11, 82, 91, -117, -107, 
    -38, -61, 60, 32, -104, 111, 106, 102, 20, -85, -40, -29, -102, 9, 116, -21, -76, -53, 35, 63, 
    -31, -5, 69, 2, 40, -117, -116, -9, 92, -78, 33, 123, 43, -93, 88, -125, 107, 60, -33, -58, 
    57, -41, -51, -125, -105, 89, -8, -33, -60, 12, -108, 14, 118, 49, 63, 114, -123, 65, -84, -99, 
    84, -106, -38, 77, 93, -102, 45, 121, 72, -103, -32, -73, 123, 119, 33, -51, 56, -64, 28, -106, 
    -51, -97, 74, 43, 13, -70, -85, -113, -97, -104, 34, -46, -121, -84, 72, 82, -33, -113, 57, -84, 
    -62, 69, -44, 70, -48, -38, -114, -3, 25, 85, -36, -66, 32, 6, 0, 116, -73, -58, 61, -69, 
    -113, 41, 44, -57, 100, 19, -59, 95, -102, 43, -109, -7, 69, 105, 92, -91, 28, -64, 106, 50, 
    -22, -85, -85, 13, 38, 51, 48, -42, -20, 59, -81, -44, 104, -48, 105, 62, -36, -67, 123, -9, 
    -121, 90, -99, -95, 17, 123, 59, 33, -24, 111, 119, 48, -124, 85, -105, 72, -75, 3, 0, 41, 
    1, 71, 93, 84, 64, -89, -108, -22, -107, -65, 62, -13, -52, 27, 111, -66, -7, -30, -72, 33, 
    15, -25, 48, 115, -100, 29, -40, 107, 114, 30, 11, Byte.MAX_VALUE, 113, 69, 86, 97, 73, 73, 73, 113, 
    -42, -118, -105, 34, 30, -34, -120, 123, 101, 72, 62, -58, 16, -42, -36, 34, -54, 41, 0, 87, 
    -81, -46, -127, -23, 45, -39, -13, 95, 94, -99, 10, 57, -67, -38, 63, 66, 77, -65, -1, 5, 
    85, 35, 111, 122, -1, -38, -119, -50, -38, -9, 123, -123, 99, 30, -108, -96, 89, -62, 12, 86, 
    -19, -51, -34, 100, -95, -5, -97, -44, 44, -4, 91, -34, 117, -109, 70, -57, 95, -120, 80, -47, 
    -84, Byte.MIN_VALUE, -97, 79, 121, -63, -43, -104, -18, -56, -52, -119, 88, 7, 6, 89, -122, 52, 49, -126, 
    53, 63, -97, 78, -91, -127, 67, -127, 95, 117, 63, 1, -52, 113, 41, -114, -50, -69, 25, -48, 
    -14, 96, 92, -99, -21, 86, -58, 113, 97, -58, 112, -116, -25, 11, 108, -4, 7, 19, 88, 77, 
    -3, -23, 89, -35, -18, 102, 7, 103, 86, 4, -10, 114, 52, 24, -18, -18, -46, -25, 110, -86, 
    61, 95, 123, -51, -31, -63, -1, -68, -93, -38, -5, -83, 43, 38, -103, -125, 1, -84, -46, 85, 
    116, -118, -64, 71, -9, -72, -35, -38, -13, -79, 2, -77, 51, 6, -122, -48, -50, 26, -40, -76, 
    32, -52, -39, 91, 25, -72, -96, -21, 45, 57, 54, 88, -17, -67, 86, -38, 62, 6, -80, -106, 
    106, 105, 20, 44, -45, 29, 93, -18, -41, -123, 13, -109, 86, 116, 109, -111, -41, -32, 109, -74, 
    36, -119, -66, 21, -99, -3, -27, -80, 63, 102, 66, -69, 14, -122, -65, -19, -21, 117, -87, -123, 
    -102, -89, 28, -76, -79, 28, 3, 104, -20, -49, -125, -20, -50, -9, 25, -102, -106, -74, 101, 106, 
    110, 103, -115, -71, -28, -113, 55, 41, -72, -67, -53, -46, -24, -24, -50, 121, -29, -82, 121, -35, 
    -111, -31, -3, 101, -89, 92, -90, -115, -11, -49, -123, 116, 106, 97, -97, -50, 3, -8, 86, 119, 
    100, -86, -53, 2, -63, -54, 12, -84, 107, 63, -48, 121, -14, 64, 69, -105, -111, -5, -89, 93, 
    -109, -3, -124, -9, 13, -30, -10, -113, 105, 99, -7, 87, -45, -40, -37, 95, -43, -65, -77, 96, 
    93, -51, 84, 103, 77, -84, 29, -126, 113, 109, -48, 63, 101, 115, 56, 108, -51, 109, -79, -94, 
    11, 86, -20, 23, -97, 125, -10, -39, -89, -85, -37, 98, -76, -65, -9, -5, -12, -75, -29, -23, 
    98, 29, 15, -91, 83, -80, 10, -26, 95, -67, -48, -34, -85, -103, -102, -41, 121, -11, 1, 24, 
    -5, -107, 97, 119, -97, -72, -72, -72, -40, -114, 24, 61, 48, 44, 44, -84, -19, -89, -23, -45, 
    -29, 90, 99, -18, 60, 103, -52, 93, 49, 1, -29, 92, -100, -66, -75, 52, -79, -78, 10, -24, 
    96, 125, 56, -21, -77, 29, -19, -79, 107, 82, 103, -29, -46, -98, 43, 103, 76, 15, -88, -63, 
    -72, -24, -26, 81, -37, -74, 116, -60, 54, -73, 49, 107, 45, -112, -65, -1, -22, -35, -123, -41, 
    -83, -95, -121, 101, -5, 27, -99, -19, -41, 96, -112, -115, -2, -93, 8, 76, 106, -29, -103, -79, 
    96, -63, -126, 121, 87, 35, 54, 6, -89, 100, -19, Byte.MAX_VALUE, 104, -57, 54, -113, 49, -7, 19, 32, 
    Byte.MAX_VALUE, -6, -105, 87, 55, 26, -116, -73, 92, -95, -123, 117, 57, -98, -42, 32, 14, -122, -83, -36, 
    113, 109, 116, -51, -48, -82, -2, 69, 56, -59, -42, -4, -105, 45, 30, -83, -74, -36, 105, 36, 
    Byte.MAX_VALUE, 59, 123, -10, 55, -17, 82, -5, -28, 9, 90, 88, 95, 126, Byte.MIN_VALUE, -39, 111, -4, -41, -17, 
    -19, -3, -9, 81, 61, -28, 104, 73, 63, -68, -117, 79, 124, -33, 35, -42, -122, -111, 22, -14, 
    -25, -77, 103, -2, -41, -53, 38, -74, -112, 22, 86, 92, 57, 94, -111, -6, -27, 76, 123, 123, 
    65, -60, -68, -68, -53, -19, -35, 15, -46, -30, 97, -87, 7, -18, -14, 96, -75, 43, -84, Byte.MIN_VALUE, 
    -4, -3, -57, -77, 103, -49, -4, -18, -35, -124, -23, 92, 58, 88, -74, -101, -15, -58, 111, 112, 
    -10, -20, 15, -19, 63, 53, 4, -68, -20, -90, -123, -39, 113, 107, 62, 110, -27, 78, -72, -85, 
    -25, 86, 107, -57, -88, 104, 32, -1, 115, -42, -119, -11, -109, 119, 21, 124, 36, 29, -84, 47, 
    49, -89, 6, 126, 114, -90, -13, -41, -114, 69, -71, -16, -25, -73, -72, -56, -38, -114, 119, 6, 
    102, 98, 63, 103, 97, -50, -124, 109, 61, 112, 109, -103, 28, -17, -68, -12, -1, -100, 113, 106, 
    -3, -32, 85, -47, -126, -92, -45, 52, -80, 38, 99, -75, -64, -28, 111, 63, 56, -45, -7, 99, 
    71, 2, 26, -45, -1, -76, 100, -41, 117, 89, -37, -79, 101, -60, 29, -121, 105, -12, 73, -84, 
    -117, -126, 86, -69, -45, -38, -79, 33, 40, -79, -11, 33, -37, 106, 117, -10, -69, -1, -13, -82, 
    11, -73, 20, 31, -53, -10, 23, -68, -91, -65, -97, 90, 19, -6, -35, Byte.MAX_VALUE, -2, 72, -126, 38, 
    36, 108, 73, -41, 62, -47, -90, 13, -79, -14, 116, 122, 19, -15, 80, 20, -16, -68, -21, 103, 
    -30, -106, -105, 3, -38, -5, -93, 56, 88, 6, 63, 124, -84, -17, -89, -32, -27, -24, -69, -42, 
    116, -98, -3, -91, 115, 90, -77, 124, -31, -72, -80, -5, -90, -51, 90, 54, Byte.MAX_VALUE, -39, -21, -45, 
    38, -36, -7, 64, -30, 110, -38, 71, 116, -126, 105, -29, -67, -9, -51, -33, 118, 77, -111, -35, 
    -79, 107, -37, -78, -121, 6, -81, 51, -76, -33, -121, -74, 59, -10, -125, 119, -17, 25, 65, 64, 
    45, 54, 86, 41, -34, 28, -54, Byte.MAX_VALUE, -37, 110, -22, -39, 107, -98, -37, -124, 89, -109, -7, -42, 
    -56, -104, -24, -60, -52, 79, 12, 22, 43, 35, -53, 59, 102, 85, -60, 77, 35, -26, 110, -72, 
    -38, -123, -37, -14, -14, -120, -101, 66, -117, -82, 118, -94, Byte.MAX_VALUE, -5, -15, -52, -103, -17, -2, -21, 
    -27, 45, 72, -33, -121, -115, -75, 30, -17, -20, -123, 118, -84, 51, 63, -69, 88, -35, -93, -65, 
    -60, 119, -51, -14, -114, 49, 63, 113, 72, -24, -67, 99, 90, -29, -34, -16, 33, 9, 5, -41, 
    110, -17, -3, -11, 87, -81, -81, -88, -103, -119, -115, -11, 18, -26, 113, 4, 63, -74, 105, -3, 
    -121, -109, -27, -43, 22, -109, 65, 87, 85, 85, -91, 51, 52, 88, 25, -72, 15, -60, -88, 43, 
    -72, 88, -111, -104, 103, -126, -76, 61, 13, Byte.MAX_VALUE, 38, 125, 49, -98, -84, -57, -60, -70, -16, 48, 
    -10, -103, 32, -65, -4, -14, -101, 79, 90, -111, 11, -65, -57, -60, -38, -69, 14, 72, -79, -59, 
    71, -59, -104, 88, -49, Byte.MAX_VALUE, 46, 62, -84, -61, 11, 48, -79, 122, 85, -117, 15, -85, -6, 17, 
    60, -84, 75, -67, -20, -94, -77, 34, 45, 125, -15, -80, -114, 69, -120, 16, 11, 6, -100, -61, 
    -62, -38, 35, -62, -10, -99, -124, -108, 98, 44, -84, 53, 42, 49, 98, 41, -25, 99, 97, -67, 
    80, 35, 70, 44, -51, 8, 28, 44, -57, -40, 22, 82, -124, 88, -90, 91, 112, -80, -102, -98, 
    16, 97, -63, 34, 73, -69, Byte.MAX_VALUE, 19, 6, -42, -59, -65, -117, 18, 11, -18, -1, 30, 3, -85, 
    126, 54, 41, 74, -84, -39, -57, 48, -80, -2, -67, 80, -100, 37, -21, -35, 18, 12, -84, 19, 
    31, -120, -77, 100, -87, -10, 97, 96, 21, 43, -59, -119, 85, -108, -121, -127, -75, -9, 35, 81, 
    98, -111, -43, -17, 97, 96, 109, 80, -117, 19, -53, -80, 24, 7, -21, -112, 56, -79, -116, 43, 
    48, -80, -34, -48, -119, 19, -53, -12, 6, 6, -42, 84, 9, 75, -62, -14, -120, -11, 38, 14, 
    -42, 41, 113, 98, 53, 62, -125, -127, 117, -81, 81, -100, 88, -106, -31, 24, 88, -63, 102, -111, 
    98, 69, 74, 88, 18, -106, -124, 37, 97, 73, 88, 18, -106, -124, -123, -127, -11, Byte.MIN_VALUE, 65, -62, 
    -110, -122, 59, 30, -62, -4, -92, -124, 37, -115, 13, 5, 51, -21, -16, -26, 97, -87, 100, 73, 
    -45, -54, 30, -62, -80, 76, -62, -94, -114, -123, 51, 7, 47, -42, -91, 48, 21, -50, 82, 88, 
    -55, 110, 9, -117, 50, 86, -99, 88, -105, -17, 75, 113, 54, -122, -92, -118, -77, 100, -67, 118, 
    68, -38, 69, 67, 25, 107, -36, 9, 12, -84, -53, -49, -120, 115, 51, -37, -83, 23, 48, -80, 
    108, 19, -60, -120, 5, 102, -84, 109, -110, -114, -79, 38, 49, 110, -64, 61, 21, -124, -75, 91, 
    121, -27, 65, 105, 107, 55, 101, -84, -68, -51, 98, -60, 90, -72, 15, 11, -21, -56, 72, 49, 
    -66, -114, 50, -28, 52, 22, 86, -99, 92, -124, 88, 4, -26, -117, 78, -88, -105, 65, -124, -81, 
    -48, -115, -63, -60, -118, 21, -29, -53, -103, 111, 98, 98, 109, 18, -31, 107, 97, 69, -5, 48, 
    -79, -50, 63, 38, 62, -84, -27, 39, 112, -113, 42, -120, 22, -33, -46, -31, 20, -36, -93, 10, 
    -48, 84, -15, 45, 29, -58, 98, 31, -126, -127, 121, -68, -118, 15, -9, -78, -76, 47, 96, 31, 
    -81, 82, -100, 35, -78, 70, 11, -46, -13, -80, -79, 78, 78, 20, 27, 86, -65, -45, -8, -121, 
    -115, 5, -119, 11, 11, 12, 114, 26, -57, -40, 77, 59, 36, 42, 45, -8, -120, -58, 49, 118, 
    -88, 80, 92, 61, 45, 72, -86, -96, -127, -27, -112, 19, 98, -62, 50, 79, 116, -48, 57, -44, 
    53, -74, 92, 76, 88, -6, -23, -76, 78, -64, 61, -112, 35, -86, 38, 107, 15, 45, -84, -117, 
    -15, 86, 17, 97, 77, -87, -28, -15, -120, 115, 31, -77, 34, 110, -79, -47, 59, 60, Byte.MAX_VALUE, -3, 
    126, 46, -97, -121, 12, 31, 55, -23, -27, 47, -49, -99, 79, -13, 75, 3, -57, 67, Byte.MIN_VALUE, 19, 
    36, -46, -46, 104, 52, -24, 85, 69, -83, 113, -48, 96, 48, -103, 9, -18, -47, 110, -93, -5, 
    89, 6, -44, 71, 15, 108, 67, -39, -51, -22, -20, 68, 69, -62, -100, -44, 69, -71, -7, -83, 
    -79, 57, 53, 117, -114, 34, 38, 85, 121, -118, -28, -44, 75, 59, 6, -47, -59, -38, -16, 54, 
    -69, 9, -122, 6, -27, 32, -71, 34, 71, 11, -42, 46, -97, -59, -122, 22, 107, 75, -7, -94, 
    16, -65, 100, 45, -121, 92, -37, -9, -48, -58, 114, 12, 50, -77, 87, -90, 44, -102, -4, -124, 
    -120, -12, 50, -85, 29, 92, 125, 20, -47, 110, 84, -58, 68, 46, 82, -101, 56, 2, -101, -46, 
    68, 27, 11, -51, -45, -79, 69, 101, 76, 11, 78, 57, 88, -45, -61, -9, 120, -99, -51, -106, 
    -87, 42, 61, 80, 81, -59, 1, 23, -104, -58, -38, -24, 99, -107, 102, -80, -110, 82, -30, 104, 
    66, -17, 76, 51, 5, 5, Byte.MIN_VALUE, -94, -64, -88, 67, 22, -42, -79, -46, 60, -42, 66, 10, 88, 
    -105, -62, Byte.MIN_VALUE, -7, 66, 101, 88, 21, -99, -94, 38, 40, 22, 24, Byte.MIN_VALUE, -14, -12, -24, -75, 122, 
    86, -117, 23, -104, -27, -51, 12, 96, -95, 105, 74, 96, -70, -60, 39, 7, 40, -51, -34, 93, 
    -76, 37, -33, 47, -115, -51, 99, -11, 32, -121, -62, 119, 70, 41, 96, -99, 11, 102, 50, -111, 
    64, 84, -91, -123, -25, -72, 44, 37, 0, -18, -113, 65, 119, -2, 73, 118, -56, -94, 42, -42, 
    62, 79, 77, 12, 103, -26, 51, -93, 104, -70, -118, -63, 35, -36, 15, 70, -60, -105, 119, -5, 
    12, 104, -101, -113, -35, 82, 83, -90, -54, 77, 79, 79, -49, 84, 29, 52, -72, 16, -125, -58, 
    50, 69, -116, -50, -50, 14, -105, -10, 85, -60, 12, -42, -71, 4, 43, 83, -91, 74, 23, 17, 
    -34, -67, -109, 75, -104, 107, -54, -42, -50, -119, -111, -53, -3, -94, 19, 50, 91, 123, -91, -15, 
    10, -71, 44, 42, 109, -65, -63, 76, 92, 119, 70, 35, -108, -11, -98, -61, -54, 6, 12, -26, 
    62, -115, -20, -120, 51, 49, -45, -84, -85, 20, 9, -27, 46, 106, 88, -74, 34, 121, -107, 82, 
    -83, 49, -127, -3, 106, 45, -76, 19, 90, 85, -10, 28, 69, 66, -78, -23, -6, 107, 40, 35, 
    -34, 98, 126, 76, 1, -90, -37, 109, 12, 97, 81, -3, -100, -69, -121, 4, -43, 4, 39, 24, 
    92, 22, 81, -62, -11, 56, 16, 90, 8, 115, 99, -9, -29, 63, -119, -125, -127, -117, -104, 126, 
    48, -62, -93, -123, -120, 41, -84, -53, -2, 102, -102, -87, 3, -78, 42, 45, -124, -95, -90, 15, 
    90, 22, -123, 42, 27, 25, -27, -86, 9, 64, -116, 97, -95, -99, 41, -12, -46, 6, 6, 69, 
    100, -103, -107, -71, -57, -124, 97, -35, -96, 124, 38, 75, 87, -58, 23, 12, 98, -95, -63, 53, 
    116, -46, 66, -84, -107, 23, 49, 91, 113, Byte.MIN_VALUE, -120, 9, 101, -82, -91, -73, -116, 111, 102, 18, 
    -117, -58, 66, 62, 88, -14, 35, 50, 27, -104, 111, -109, 15, -59, -92, 104, -103, -39, -57, 9, 
    -39, 91, 17, -109, 88, -105, -79, -57, 60, -96, -14, -53, 110, 96, -27, 105, 79, 28, 13, 72, 
    109, 97, -30, -55, 99, -110, -37, 24, -59, 66, -21, -45, 113, -110, 5, 80, -107, 24, -55, -38, 
    40, 5, 96, 109, -24, 126, 11, -19, -85, -61, 43, 59, 17, -77, 88, -56, 15, -93, 119, 3, 
    53, -119, -118, -14, 22, 54, 71, 116, -6, -12, 72, 21, -35, 62, -67, -31, 94, -60, 52, 86, 
    69, -110, -9, -97, -109, -49, 15, 40, 96, 123, 34, -54, -39, 123, 123, -100, -34, 107, 51, -16, 
    118, 41, -29, 88, 104, -110, 119, -33, 74, 1, 40, 122, 60, -83, -111, -125, 73, 59, -40, 31, 
    -71, -42, 72, -29, -61, -32, -43, 119, -38, -104, -57, 58, 50, -52, -18, 85, 5, 9, 79, 52, 
    114, 51, 29, 12, -106, -126, -64, 92, -20, -25, 34, -12, -83, 64, -52, 99, -95, 81, -44, -5, 
    74, 96, -54, 14, 80, 115, -73, -44, 0, -90, -104, 72, -36, -39, 27, -27, 12, -60, 6, -42, 
    -91, -66, 13, 84, 107, -58, -26, -16, 28, -126, -37, -59, -39, -93, -118, 120, -100, -89, 46, 88, 
    -17, -65, -64, 10, 22, -38, -73, -111, 90, 2, -86, -126, -25, 112, -65, -88, 12, 7, -3, 48, 
    58, -50, -80, 106, 43, 98, 7, -85, 121, 4, -123, -18, 3, -24, -105, 71, -106, -13, -79, 
    0, 15, -106, -76, -88, 34, 47, -5, 41, -96, 13, 106, 102, 9, 11, 125, -29, 113, 45, 31, 32, 
    51, 80, 69, -16, -77, 89, 1, 72, 125, 74, -72, 119, -33, -15, 38, -62, -49, 35, -74, -80, 
    -48, -85, 61, Byte.MAX_VALUE, -5, 25, 90, -76, -95, -119, -64, -29, -50, 74, 40, 15, 88, -21, -59, -102, 
    48, 40, -41, 32, -10, -80, 28, 99, 76, 61, 118, 16, -29, 21, 26, 126, 55, -95, 2, -111, 
    31, -107, 73, -11, 118, 65, 67, 88, 19, -117, 88, -88, 116, -92, -5, -124, 64, -90, 92, 45, 
    Byte.MIN_VALUE, -17, 26, 17, -23, 84, 123, 45, 16, -4, 21, 98, 19, 11, -51, 114, 91, 17, 33, 59, 
    -115, 16, -60, -34, 102, -69, 46, 58, -75, -118, 74, 74, -42, 45, 64, -20, 98, 57, -18, 113, 
    -5, 62, -113, -123, 20, 72, 64, -117, 38, -100, -62, -85, -110, -6, 88, 27, -53, 88, -88, 46, 
    -38, 23, 118, -103, -126, -25, -17, 20, -61, -35, -57, 16, -37, 88, -104, 51, 91, -36, -9, 35, 
    60, -3, -123, -92, 44, -60, 62, 22, -70, -89, -32, 70, 120, -19, -94, 40, 22, 113, -127, 101, 
    27, -22, -5, 31, -74, 5, -61, -64, 43, -100, 96, -95, 35, 119, -5, -68, 85, 99, -33, -29, 
    -120, 27, 44, -108, 21, -29, -29, 69, -53, -102, -80, 23, 113, -123, -123, -26, 109, -12, 105, 45, 
    -56, Byte.MAX_VALUE, 21, 113, -121, -43, 60, 74, -19, -61, 90, 80, 112, 87, 51, -121, 88, -88, -74, -113, 
    -58, 103, -75, 64, 115, 107, 19, -30, 18, 11, -43, 5, -42, -8, -86, -107, 105, -16, 57, -60, 
    45, 22, 58, 54, -46, 71, -117, 22, 4, 126, -119, -72, -58, 66, 121, -61, 27, 124, -47, -54, 
    28, -67, 7, 113, -113, -123, -66, 120, -50, 7, -117, -107, 61, 97, 31, -30, 3, 11, 45, 94, 
    14, -66, 86, 21, 33, 113, 22, -30, 7, 11, -83, 124, -50, -73, -34, 115, 5, 72, -7, 7, 
    -30, 11, 11, -67, -18, 91, -25, 47, -61, -86, -7, -120, 63, 44, -37, -52, 20, 31, 42, 91, 
    -42, -92, -7, 54, 30, -79, 16, -38, -12, 28, -8, 76, 29, 76, -38, 68, 51, -77, 116, -79, 
    -48, 50, -97, 105, -27, 19, 95, 71, 124, 99, -95, -107, -113, -7, -60, -87, 34, -60, 99, 89, 
    -120, Byte.MAX_VALUE, 44, -12, 113, -108, 15, 20, 45, 8, -35, -119, -124, Byte.MIN_VALUE, -123, -10, -124, -22, 5, -50, 
    5, -6, 97, 121, 72, 24, 88, -24, 100, -96, 86, -48, 90, -96, -23, -9, 45, 18, 10, 22, 
    58, -33, -89, 76, -56, 86, -121, -4, -49, 35, -31, 96, -95, -38, -55, -21, -124, -6, 80, 4, 
    120, 123, 122, 29, 18, 18, 22, -78, 45, -119, 18, 104, -9, -44, 50, 124, 30, 67, 121, 100, 
    12, 11, -95, -68, 97, 58, 1, -106, 45, -48, -115, -52, 67, -62, -61, 66, -57, -121, 102, -125, 
    -32, -86, 96, 78, -65, -109, 72, -120, 88, 8, -115, 74, 52, 11, -118, 11, -120, 57, 19, 28, 
    72, -96, 88, 40, 111, -112, -112, 86, 125, 64, 29, -70, -121, -47, -20, 49, -117, -123, -102, -18, 
    -55, 16, -116, 22, -84, -69, -21, 18, 18, 50, 22, 114, -52, 31, 41, -120, 14, 42, Byte.MIN_VALUE, 118, 
    -36, -21, 14, 36, 108, 44, 103, 119, 126, 76, -118, 21, -8, 111, -83, -110, -58, 84, 50, -98, 
    53, -26, -79, 16, 122, 63, 64, -57, -13, -34, 82, -48, -9, -37, -60, 66, -58, -40, -64, 66, 
    39, 39, -65, -62, -25, -82, 36, -48, 39, -115, 58, -119, 124, 5, 11, 57, 42, -6, 101, -13, 
    54, -4, -127, -52, -34, 21, 54, -28, 59, 88, 8, 53, -65, 62, 76, -51, -57, -26, 101, 32, 
    14, 15, 91, -42, -52, 82, -90, -40, -62, 66, -24, -36, -46, 33, 42, -82, 75, 23, 64, -39, 
    -16, 37, -25, 88, -53, 18, 123, 88, 8, -43, -10, 87, 24, 57, -43, -126, -102, -119, 65, -89, 
    89, -52, 16, -101, 88, 8, -107, 76, 72, 41, -25, -86, 116, 1, -108, 39, 77, 46, 97, 53, 
    59, -20, 98, 33, -37, -55, -15, 3, -72, -31, -78, -85, -121, 79, 58, -18, 64, -66, -116, -27, 
    -116, 111, -18, 27, -89, 54, -77, -21, 5, 96, -42, -116, 27, -11, 21, -21, 89, 97, 31, 11, 
    -95, -54, -107, 17, 25, 70, -10, -72, -64, 110, -56, -120, 88, 83, -55, 65, 70, -72, -64, 114, 
    -42, -58, 29, 55, 37, -77, -75, 2, 4, 53, -119, -67, -74, 56, 56, -55, 6, 55, 88, -50, 
    40, 124, 106, -36, -10, 42, -122, 15, 75, 118, 94, 77, -69, 125, -54, -40, 66, -82, -14, -64, 
    25, 22, 114, 92, 44, 25, -13, -25, 124, -126, 57, 46, 0, 34, -9, -74, 49, -97, -42, 59, 
    -48, -115, -121, -43, 26, 23, -106, -35, 50, 91, 101, -80, -48, 7, 3, -80, 24, -117, 102, -33, 
    -78, -78, -98, -45, -28, 115, -117, -27, 44, 95, -107, 121, 51, 98, 94, 81, 53, -40, -15, -63, 
    90, 15, -23, 84, -67, 18, 61, 35, -81, -46, -63, 113, -30, -71, -58, 106, 107, -18, -65, -98, 
    43, 11, -55, -44, -31, 113, 1, -24, -14, 35, -28, 51, -66, -74, -15, -112, 112, 62, -80, -38, 
    -106, -126, -74, -114, 29, 51, 59, -29, 115, -115, -63, 74, -19, 59, 12, 109, Byte.MAX_VALUE, -53, -88, -3, 
    -4, -125, -39, -93, -97, -38, 90, -55, 83, -94, -7, -62, 114, -58, -27, -70, -54, -68, -105, -18, 
    -8, -13, -61, 25, -86, -102, -114, 19, 74, -35, 41, 57, -1, -73, 81, -107, -7, -32, 109, -95, 
    111, 108, 59, -15, -17, -53, -4, -91, -104, 71, -84, -114, 58, -7, 77, -34, -28, 62, -2, -125, 
    39, -66, -107, -83, 42, 55, 24, 76, -50, 48, 91, -38, -93, -63, -7, -77, -63, -88, 41, -54, 
    125, 119, -54, -32, 63, -7, 79, -53, -6, -6, 2, -33, 105, -27, 29, -85, 93, -84, -18, 100, 
    105, 113, -34, -42, -59, -117, -33, 112, -58, -72, -56, -74, 24, -2, -94, -13, -25, -59, -117, -77, 
    -14, -118, 75, 43, -21, -102, 5, -111, 76, 97, 96, -7, 72, 72, 88, 18, -106, -124, 37, 97, 
    73, 88, 18, -106, 20, 18, -106, -124, 37, 97, 73, 88, 18, -106, -124, 37, -123, -124, 37, 97, 
    73, 88, 18, -106, -124, 37, 97, 73, 33, 97, 73, 88, 18, -106, -124, -27, 75, -15, -1, 58, 
    -37, -102, -15, 114, -81, 89, -22, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126 };
  
  public Sad() {}
}
