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
| `getFirmwareStatus()` | Get the printer firmware status. Returns: 0--unknow， A5--bootloader, C3--print |
| `printerSelfChecking()` | The printer will print a self-test page |
| `getPrinterVersion()` |  Get printer firmware version number |
| `lineWrap(int n, SunmiCallback cb)` | Printer feeds `n` numbers of lines  |
| `sendRAWData(byte[] data, SunmiCallback cb)` | Send epson instruction commands (ESC/POS) as `byte[]`  |
| `runRAWData(int[] decimals, SunmiCallback cb)` | Send epson instruction commands (ESC/POS) as `int[]` (helpfull for JS Frameworks) |
| `setAlignment(int a, SunmiCallback cb)` | Set alignment mode where `0` is left, `1` is center and `2` is right |
| `setFontName(String typeface, SunmiCallback callback)` | Set printer fontname |
| `setFontSize(float fontSize, SunmiCallback callback)` | Set printer font size |
| `printText(String text, SunmiCallback callback)` | When the printed content is less than one or more lines, you need to add a line break `\n` at the end of the content to print it immediately, otherwise it will be cached in the buffer|
| `printTextWithFont(String text, String typeface, float fontSize, SunmiCallback callback)` | Print a text with a typeface and a fontsize. It applies same rules as the function above |
| `` |  |
| `` |  |


---
# License
MIT

