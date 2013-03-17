package com.lastcrusade.soundstream.net.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.util.Log;

public class PlayStatusMessage extends ADataMessage {
	
	private final String TAG = PlayStatusMessage.class.getName();
	private final String PLAY_MESSAGE = "Play";
	private final String PAUSE_MESSAGE = "Pause";
	private String string = "";
	
	public PlayStatusMessage() {}

	public PlayStatusMessage(String playStatusMessage) {
		if(playStatusMessage.equals(PLAY_MESSAGE) || playStatusMessage.equals(PAUSE_MESSAGE)) {
			this.setString(playStatusMessage);
		}
		else {
			Log.wtf(TAG, "Status msg passed not Play or Pause");
		}
	}

	@Override
	public void deserialize(InputStream input) throws IOException {
		byte[] bytes = new byte[1024];
		int read = 0;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while((read = input.read(bytes)) > 0) {
			out.write(bytes, 0, read);
		}
		this.setString(out.toString());
	}

	@Override
	public void serialize(OutputStream output) throws IOException {
		output.write(getString().getBytes());
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
