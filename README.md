# Android Library for SunmiV2

### Folder structure
| Folder | Content |
| ------ | ------ |
| out | Android Library (ready to use) |
| src | Source code of the Android Library (as Android Studio project) |

### How to use it
We implemented this library to include it in a Nativescript project, but you could use it in a Android native app or a Android based Javascript framework. This library raise a service with an Intent so we separate the printing process from the UI.

You should copy the `SunmiV2.aar` into your `lib` folder. Then you should import it or call it directly. Here is an example in a JS Framework (using Typescript).

```typescript
let printer: any = null;
let context: any = ad.getApplicationContext(); // Replace this with the Android context.

try {
     printer = com.sunmi.sunmiv2.services.SunmiPrinter.getInstance();
     printer.initPrinter(context);
     
     // The second paramenter is the SunmiCallback, but for simplicity purpose we are going to ignore it.
     printer.printText('Hello World!', null); 
} catch (err) {
    console.log(err);
}
```
Once we have our printer instance, we can use the functions provide by Sunmi. Read the documentation below to learn about what you can do.

### Documentation
Here is a list of the functions that have been implementes on the Library using the `AIDL` files.

| Function | Comment |
| ------ | ------ |
| `printerInit(SunmiCallback cb)` | Reset the printer's logical program (e.g.: typography, bolding, etc.), but it doesn't clear the cache data, so the unfinished printing will continue after the reset. |
| `getPrinterSerialNo()` | Get the printer board serial number |
| `getPrinterModal()`| Get printer interface (print head size) |
| `getServiceVersion()`| Get the printer service version number | 
| `getPrintedLength(SunmiCallback cb)`| Get the print head print length |
| `getFirmwareStatus()` | Get the printer firmware status. Returns: `0`is unknow，`A5`is bootloader, `C3` is print |
| `printerSelfChecking()` | The printer will print a self-test page |
| `getPrinterVersion()` |  Get printer firmware version number |
| `lineWrap(int n, SunmiCallback cb)` | Printer feeds `n` numbers of lines  |
| `sendRAWData(byte[] data, SunmiCallback cb)` | Send epson instruction commands (ESC/POS) as `byte[]`  |
| `runRAWData(int[] decimals, SunmiCallback cb)` | Send epson instruction commands (ESC/POS) as `int[]` (helpful for JS Frameworks) |
| `setAlignment(int a, SunmiCallback cb)` | Set alignment mode where `0` is left, `1` is center and `2` is right |
| `setFontName(String typeface, SunmiCallback callback)` | Set printer fontname |
| `setFontSize(float fontSize, SunmiCallback callback)` | Set printer font size |
| `printText(String text, SunmiCallback callback)` | When the printed content is less than one or more lines, you need to add a line break `\n` at the end of the content to print it immediately, otherwise it will be cached in the buffer|
| `printTextWithFont(String text, String typeface, float fontSize, SunmiCallback callback)` | Print a text with a typeface and a fontsize. It applies same rules as the function above |
| `printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, SunmiCallback callback)` | Print a row of the table, you can specify the column width and alignment. `colsTextArr`: array of text strings in each column. `colsWidthArr`: array of column widths (calculated with English characters, each Chinese character occupies two English characters, each width is greater than 0). `colsAlign`: alignment of each column (`0` is left, `1` is center and `2` is right) |
| `printBitmap(Bitmap bitmap, SunmiCallback callback)` |  Print a bitmap picture. `bitmap`: The maximum width is `384` pixels, if the width is exceeded, the display will be incomplete; the picture size is `height * width` < 8M |
| `printBarCode(String data, int symbology, int height, int width, int textposition, SunmiCallback callback)` | Print 1D barcode. `data`: barcode data. `symbology`: barcode type (`0-UPC-A`, `UPC-E`, `JAN13(EAN13)`, `JAN8(EAN8)`, `CODE39`, `ITF`, `CODABAR`, `CODE93`, `CODE128`). `height`: barcode height, value is `1` to `255`, default is `162`. `width`: barcode width, value `2` to `6`, default `2`. `textposition`: text position (`0` means no text, `1` means text above the barcode, `2` means text below the barcode, `3` means both above and below the barcode)|
| `printQRCode(String data, int moduleSize, int errorLevel, SunmiCallback callback)` | Print 2D barcode. `data`: QR code data. `modulesize`: QR code block size (unit: point, value `1` to `16`). `errorlevel`: QR code error correction level (`0` to `3`), |
| `printOriginalText(String text, SunmiCallback callback)` |  Print text. If the text width is one line, it will wrap automatically. The text is output as the vector text width, that is, each character is not the same width |
| `commitPrinterBuffer(SunmiCallback callback)` | Print buffer content |
| `enterPrinterBuffer(boolean clean, SunmiCallback callback)` | Enter transaction mode, all print calls will be cached. `clean`: whether to clear the cached buffer contents if the transaction mode has not been exited before |
| `exitPrinterBuffer(boolean commit, SunmiCallback callback)` | Exit buffer mode. `commit`: whether to print out the contents of the buffer |
| `printBitmapCustom(Bitmap bitmap, int type, SunmiCallback callback) ` | Print a custom bitmap picture. `bitmap`: image bitmap object (maximum width of `384` pixels, images over 1M cannot be printed). `type`: There are currently two printing methods (`0` means same as printBitmap. `1` means black and white picture with threshold 200, gray picture) |

### Some Helpful ESC/POS commands

You can apply any ESC/POS, here is some example how to use it (in decimals):

##### Bolding text

```typescript
public setTextBold(bold: boolean = true): void {
    this.printer.runRAWData([27, 69, bold ? 1 : 0], null);
}
```

##### Inverting White/Black

```typescript
public setInvertedWhiteBlack(inverted: boolean = true): void {
    this.printer.runRAWData([29, 66, inverted ? 1 : 0], null);
}
```
---
# License
MIT
