package com.sunmi.sunmiv2.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;

import com.sunmi.sunmiv2.contracts.SunmiCallback;

import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;

public class SunmiPrinter {
    private static final SunmiPrinter printer = new SunmiPrinter();
    private IWoyouService woyouService = null;
    private final String UNREACHABLE = "unreachable";

    private ServiceConnection connService = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            woyouService = null;
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            woyouService = IWoyouService.Stub.asInterface(service);
        }
    };

    private SunmiPrinter(){}

    public static SunmiPrinter getInstance() {
        return printer;
    }

    public void initPrinter(Context context){
        Intent intent = new Intent();
        intent.setPackage("woyou.aidlservice.jiuiv5");
        intent.setAction("woyou.aidlservice.jiuiv5.IWoyouService");
        context.startService(intent);
        context.bindService(intent, connService, Context.BIND_AUTO_CREATE);
    }

    // --------------------------------

    public int getFirmwareStatus() {
        if (woyouService != null) {
            try {
                return woyouService.getFirmwareStatus();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public String getServiceVersion() {
        if (woyouService != null) {
            try {
                return woyouService.getServiceVersion();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return UNREACHABLE;
    }

    public void printerInit(SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printerInit(makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void printerSelfChecking(SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printerSelfChecking(makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public String getPrinterSerialNo() {
        if (woyouService != null) {
            try {
                return woyouService.getPrinterSerialNo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return UNREACHABLE;
    }

    public String getPrinterVersion() {
        if (woyouService != null) {
            try {
                return woyouService.getPrinterVersion();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return UNREACHABLE;
    }

    public String getPrinterModal() {
        if (woyouService != null) {
            try {
                return woyouService.getPrinterModal();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return UNREACHABLE;
    }

    public void getPrintedLength(SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.getPrintedLength(makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void lineWrap(int n, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.lineWrap(n, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void sendRAWData(byte[] data, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.sendRAWData(data, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void runRAWData(int[] decimals, SunmiCallback callback) {
        byte[] aux = new byte[3];

        for(int i = 0; i < decimals.length; i++) {
            aux[i] = (byte) decimals[i];
        }

        this.sendRAWData(aux, callback);
    }

    public void setAlignment( int alignment, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.setAlignment(alignment, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void setFontName(String typeface, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.setFontName(typeface, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void setFontSize(float fontSize, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.setFontSize(fontSize, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void printText(String text, SunmiCallback callback) {
        if  (woyouService != null) {
            try {
                woyouService.printText(text, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void printTextWithFont(String text, String typeface, float fontSize, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printTextWithFont(text, typeface, fontSize, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printColumnsText(colsTextArr, colsWidthArr, colsAlign, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void printBitmap(Bitmap bitmap, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printBitmap(bitmap, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void printBarCode(String data, int symbology, int height, int width, int textposition, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printBarCode(data, symbology, height, width, textposition, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void printQRCode(String data, int moduleSize, int errorLevel, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printQRCode(data, moduleSize, errorLevel, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void printOriginalText(String text, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printOriginalText(text, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void commitPrinterBuffer(SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.commitPrinterBuffer();
                if (callback != null) {
                    callback.onRunResult(true);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void enterPrinterBuffer(boolean clean, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.enterPrinterBuffer(clean);
                if (callback != null) {
                    callback.onRunResult(true);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void exitPrinterBuffer(boolean commit, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.exitPrinterBuffer(commit);
                if (callback != null) {
                    callback.onRunResult(true);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    public void printBitmapCustom(Bitmap bitmap, int type, SunmiCallback callback) {
        if (woyouService != null) {
            try {
                woyouService.printBitmapCustom(bitmap, type, makeCallback(callback));
            } catch (RemoteException e) {
                e.printStackTrace();
                ThrowErrorCallback(callback);
            }
        }
        else {
            ThrowErrorCallback(callback);
        }
    }

    private void ThrowErrorCallback(SunmiCallback callback) {
        if (callback != null) {
            try {
                callback.onRaiseException(500, UNREACHABLE);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private ICallback makeCallback(final SunmiCallback callback) {
        if (callback != null) {
            return new ICallback.Stub() {
                @Override
                public void onRunResult(boolean isSuccess) throws RemoteException {
                    callback.onRunResult(isSuccess);
                }

                @Override
                public void onReturnString(String result) throws RemoteException {
                    callback.onReturnString(result);
                }

                @Override
                public void onRaiseException(int code, String msg) throws RemoteException {
                    callback.onRaiseException(code, msg);
                }
            };
        }
        return null;
    }
}