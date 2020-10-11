package com.zipsearch.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipsearch.model.Message;
import com.zipsearch.model.ZipSearchCriteria;

/**
 * @author Yashwanth Kalva
 * 
 */
public class ZipSearchManager implements IZipSearchManager {

	public static int counter = 0;
	public static int tsLineNumber = 0;
	public static boolean hasWord = false;
	public static String tsValue = null;

	@Override
	public void scanZipFileForWord(String zipFilePath, ZipSearchCriteria criteria) {
		try (ZipFile zipFile = new ZipFile(zipFilePath)) {
			Predicate<ZipEntry> isFile = zipEntry -> !zipEntry.isDirectory();
			Predicate<ZipEntry> isJsonFile = zipEntry -> zipEntry.getName().matches(".*json");
			if (zipFilePath.contains("/")) {
				System.out.println(
						"zip=" + zipFilePath.substring(zipFilePath.lastIndexOf('/') + 1, zipFilePath.length()));
			} else if (zipFilePath.contains("\\")) {
				System.out.println(
						"zip=" + zipFilePath.substring(zipFilePath.lastIndexOf('\\') + 1, zipFilePath.length()));
			} else {
				System.out.println("zip=" + zipFilePath);
			}
			zipFile.stream().filter(isFile.and(isJsonFile)).forEach(zipEntry -> {
				readJsonFile(zipFile, zipEntry, criteria);
			});
		}

		catch (IOException e) {
			e.printStackTrace();

		}
	}

	private void readJsonFile(ZipFile zipFile, ZipEntry fileEntry, ZipSearchCriteria criteria) {

		try (InputStream inputStream = zipFile.getInputStream(fileEntry);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			// reader.lines().forEach(line -> patternSearch(fileEntry, line, criteria));
			criteria.setFileName(fileEntry.getName());
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			List<Message> messagesList = objectMapper.readValue(reader, new TypeReference<List<Message>>() {
			});
			if (messagesList != null & messagesList.size() > 0) {
				messagesList.stream().forEach(message -> patternSearch(message, criteria));
			}
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	private void patternSearch(Message message, ZipSearchCriteria criteria) {
		if (message.getText() != null && message.getText().length() > 0) {
			int startIndex, endIndex;
			Matcher matcher = Pattern.compile(criteria.getKeyword(), Pattern.CASE_INSENSITIVE)
					.matcher(message.getText());
			if (matcher.find()) {
				counter++;
				if (counter <= criteria.getResultCount() && criteria.getResultCount() != 0) {
					startIndex = matcher.start() - criteria.getNeighborLimit();
					startIndex = startIndex < matcher.regionStart() ? matcher.regionStart() : startIndex;
					endIndex = matcher.end() + criteria.getNeighborLimit();
					endIndex = endIndex > matcher.regionEnd() ? matcher.regionEnd() : endIndex;

					System.out.println("file=" + criteria.getFileName() + " ts=" + message.getTs() + " : '"
							+ message.getText().substring(startIndex, endIndex) + "'");
				}
			}

		}
	}

}
