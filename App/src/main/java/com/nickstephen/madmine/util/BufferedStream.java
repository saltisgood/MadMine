package com.nickstephen.madmine.util;

import android.os.Build;

import com.nickstephen.lib.misc.BitConverter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Nick Stephen on 28/04/2014.
 */
public class BufferedStream {
    private static final int BUFFER_SIZE = 0x200;

    private static final int SIZEOF_BYTE = 1;
    private static final int SIZEOF_CHAR = 2;
    private static final int SIZEOF_SHORT = SIZEOF_CHAR;
    private static final int SIZEOF_INT = 4;

    private final InputStream mInput;
    private final int mBufferSize;
    private byte[] mBuffer;

    private int mStartReadPos = 0;
    private int mCurrentReadPos = 0;
    private int mEndReadPos = 0;
    private int mBufferPos = 0;

    public BufferedStream(@NotNull InputStream is) {
        this(is, BUFFER_SIZE);
    }

    public BufferedStream(@NotNull InputStream is, int bufferSize) {
        if (bufferSize <= SIZEOF_INT) {
            bufferSize = SIZEOF_INT + 1;
        }

        mInput = is;
        mBufferSize = bufferSize;
        mBuffer = new byte[mBufferSize];
    }

    public void close() throws IOException {
        mInput.close();
    }

    private int read(int minSize) throws IOException {
        try {
            int diff = mEndReadPos - mCurrentReadPos;

            if (diff < minSize) {
                if (diff != 0) {
                    System.arraycopy(mBuffer, mBufferPos, mBuffer, 0, diff);
                }

                mBufferPos = 0;
                mStartReadPos = mCurrentReadPos;

                int len = 0;
                while ((diff + len) < minSize) {
                    len += mInput.read(mBuffer, diff + len, mBufferSize - len - diff);
                }

                mEndReadPos += len;
            }

            mCurrentReadPos += minSize;
            return minSize;
        } catch (IndexOutOfBoundsException e) {
            if (Build.VERSION.SDK_INT >= 9) {
                throw new IOException("Read past end of asset stream!", e);
            } else {
                throw new IOException("Read past end of asset stream!");
            }
        }
    }

    public byte readByte() throws IOException {
        read(SIZEOF_BYTE);

        return mBuffer[mBufferPos++];
    }

    public char readChar() throws IOException {
        read(SIZEOF_CHAR);

        char val = BitConverter.toChar(mBuffer, mBufferPos);
        mBufferPos += SIZEOF_CHAR;
        return val;
    }

    public short readShort() throws IOException {
        read(SIZEOF_SHORT);

        short val = BitConverter.toInt16(mBuffer, mBufferPos);
        mBufferPos += SIZEOF_SHORT;
        return val;
    }

    public int readInt() throws IOException {
        read(SIZEOF_INT);

        int val = BitConverter.toInt32(mBuffer, mBufferPos);
        mBufferPos += SIZEOF_INT;
        return val;
    }

    public void skip(int byteCount) throws IOException { // Could be optimised //
        while (byteCount > 0) {
            if (byteCount > mBufferSize) {
                byteCount -= read(mBufferSize);
            } else {
                byteCount -= read(mBufferSize);
            }
        }
    }
}
