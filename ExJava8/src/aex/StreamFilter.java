package aex;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class StreamFilter {
	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get("src/aex/data.txt")), StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split(" "));

		long count = 0;
		for (String w : words) {
			if (w.length() > 10) {
				count++;
				System.out.println(w);
			}
		}
		System.out.println(count);

		count = words.stream().filter(w -> w.length() > 10).count();
		System.out.println(count);

		count = words.parallelStream().filter(w -> w.length() > 10).count();
		System.out.println(count);
	}
}
