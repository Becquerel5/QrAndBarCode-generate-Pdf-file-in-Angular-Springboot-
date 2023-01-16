package net.becquerel.springboot.Exportdoc;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.barcodes.qrcode.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

@Service
public class QrAndBarCode {
    public static byte[] getQRCodeImage(String text,int width,int height){
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            return null;
        }
    }

    public static byte[] getBarCodeImage(String text,int width,int height) throws WriterException, IOException {
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);
            Writer writer = new Code128Writer();
            BitMatrix bitMatrix = writer.encode(text,BarcodeFormat.CODE_128,width,height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        }catch (Exception e ){
            System.err.println(e.getMessage());
            return null;
        }
    }

















































}
