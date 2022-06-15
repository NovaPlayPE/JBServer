package net.novatech.jbserver.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.google.common.base.Function;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.novatech.library.math.*;
import net.novatech.library.math.motion.Rotation;
import net.novatech.library.math.vector.Vector3d;
import net.novatech.library.nbt.tags.*;

public class Utils {
	
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static void writeFile(String fileName, String content) throws IOException {
		writeFile(fileName, new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
	}

	public static void writeFile(String fileName, InputStream content) throws IOException {
		writeFile(new File(fileName), content);
	}

	public static void writeFile(File file, String content) throws IOException {
		writeFile(file, new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
	}

	public static void writeFile(File file, InputStream content) throws IOException {
		if (content == null) {
			throw new IllegalArgumentException("content must not be null");
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream stream = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = content.read(buffer)) != -1) {
			stream.write(buffer, 0, length);
		}
		stream.close();
		content.close();
	}

	public static String readFile(File file) throws IOException {
		if (!file.exists() || file.isDirectory()) {
			throw new FileNotFoundException();
		}
		return readFile(new FileInputStream(file));
	}

	public static String readFile(String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists() || file.isDirectory()) {
			throw new FileNotFoundException();
		}
		return readFile(new FileInputStream(file));
	}

	public static String readFile(InputStream inputStream) throws IOException {
		return readFile(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
	}

	private static String readFile(Reader reader) throws IOException {
		BufferedReader br = new BufferedReader(reader);
		String temp;
		StringBuilder stringBuilder = new StringBuilder();
		temp = br.readLine();
		while (temp != null) {
			if (stringBuilder.length() != 0) {
				stringBuilder.append("\n");
			}
			stringBuilder.append(temp);
			temp = br.readLine();
		}
		br.close();
		reader.close();
		return stringBuilder.toString();
	}

	public static String getExceptionMessage(Throwable e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}

	public static UUID dataToUUID(String... params) {
		StringBuilder builder = new StringBuilder();
		for (String param : params) {
			builder.append(param);
		}
		return UUID.nameUUIDFromBytes(builder.toString().getBytes(StandardCharsets.UTF_8));
	}

	public static UUID dataToUUID(byte[]... params) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		for (byte[] param : params) {
			try {
				stream.write(param);
			} catch (IOException e) {
				break;
			}
		}
		return UUID.nameUUIDFromBytes(stream.toByteArray());
	}

	public static String rtrim(String s, char character) {
		int i = s.length() - 1;
		while (i >= 0 && (s.charAt(i)) == character) {
			i--;
		}
		return s.substring(0, i + 1);
	}

	public static boolean isByteArrayEmpty(final byte[] array) {
		for (byte b : array) {
			if (b != 0) {
				return false;
			}
		}
		return true;
	}

	public static long toRGB(byte r, byte g, byte b, byte a) {
		long result = (int) r & 0xff;
		result |= ((int) g & 0xff) << 8;
		result |= ((int) b & 0xff) << 16;
		result |= ((int) a & 0xff) << 24;
		return result & 0xFFFFFFFFL;
	}

	public static int toInt(Object number) {
        if (number instanceof Integer) {
            return (Integer) number;
        }

        return (int) Math.round((double) number);
    }

	public static byte[] parseHexBinary(String s) {
		final int len = s.length();
		if (len % 2 != 0)
			throw new IllegalArgumentException("hexBinary needs to be even-length: " + s);

		byte[] out = new byte[len / 2];

		for (int i = 0; i < len; i += 2) {
			int h = hexToBin(s.charAt(i));
			int l = hexToBin(s.charAt(i + 1));
			if (h == -1 || l == -1)
				throw new IllegalArgumentException("contains illegal character for hexBinary: " + s);

			out[i / 2] = (byte) (h * 16 + l);
		}

		return out;
	}

	private static int hexToBin(char ch) {
		if ('0' <= ch && ch <= '9')
			return ch - '0';
		if ('A' <= ch && ch <= 'F')
			return ch - 'A' + 10;
		if ('a' <= ch && ch <= 'f')
			return ch - 'a' + 10;
		return -1;
	}
	

	
	public static CompoundTag getDefaultEntityPosition(Vector3d posVector) {
		return getDefaultEntityPosition(posVector, new Vector3d(0,0,0));
	}
	
	public static CompoundTag getDefaultEntityPosition(Vector3d posVector, Vector3d motionVector) {
		return getDefaultEntityPosition(posVector, motionVector, new Rotation(0,0));
	}
	
	public static CompoundTag getDefaultEntityPosition(Vector3d posVector, Vector3d motionVector, Rotation rotation) {
		CompoundTag nbt = new CompoundTag("").
				add(new ListTag("Pos")
						.add(new DoubleTag("", posVector.getX()))
						.add(new DoubleTag("", posVector.getY()))
						.add(new DoubleTag("", posVector.getZ())))
				.add(new ListTag("Motion")
						.add(new DoubleTag("", motionVector.getX()))
						.add(new DoubleTag("", motionVector.getY()))
						.add(new DoubleTag("", motionVector.getZ())))
				.add(new ListTag("Rotation")
						.add(new FloatTag("", rotation.getYaw()))
						.add(new FloatTag("", rotation.getPitch())));
		return nbt;
	}
	
	public static JsonArray toArray(Object... objects) {
		List array = new ArrayList();
		Collections.addAll(array, objects);
		return GSON.toJsonTree(array).getAsJsonArray();
	}

	public static JsonObject toObject(Object object) {
		return GSON.toJsonTree(object).getAsJsonObject();
	}

	public static <E> JsonObject mapToObject(Iterable<E> collection, Function<E, JSONPair> mapper) {
		Map object = new LinkedHashMap();
		for (E e : collection) {
			JSONPair pair = mapper.apply(e);
			if (pair != null) {
				object.put(pair.key, pair.value);
			}
		}
		return GSON.toJsonTree(object).getAsJsonObject();
	}

	public static <E> JsonArray mapToArray(E[] elements, Function<E, Object> mapper) {
		ArrayList array = new ArrayList();
		Collections.addAll(array, elements);
		return mapToArray(array, mapper);
	}

	public static <E> JsonArray mapToArray(Iterable<E> collection, Function<E, Object> mapper) {
		List array = new ArrayList();
		for (E e : collection) {
			Object obj = mapper.apply(e);
			if (obj != null) {
				array.add(obj);
			}
		}
		return GSON.toJsonTree(array).getAsJsonArray();
	}

	public static class JSONPair {
		public final String key;
		public final Object value;

		public JSONPair(String key, Object value) {
			this.key = key;
			this.value = value;
		}

		public JSONPair(int key, Object value) {
			this.key = String.valueOf(key);
			this.value = value;
		}
	}

}