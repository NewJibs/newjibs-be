package com.ssafy.newjibs.house.init;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

	private static final int BATCH_SIZE = 1000; // set size

	private final ZipFileService zipFileService;
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public ApplicationStartupRunner(ZipFileService zipFileService, DataSource dataSource) {
		this.zipFileService = zipFileService;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		String resourcePath = "data.zip";  // target
		String tempDir = System.getProperty("java.io.tmpdir"); // temp directory

		// unzip
		zipFileService.unzipResourceFile(resourcePath, tempDir);

		// execute sql file
		String sqlFilePath = tempDir + "/data.sql";

		try (Stream<String> lines = Files.lines(Paths.get(sqlFilePath))) {
			List<String> batch = new ArrayList<>();
			lines.filter(line -> !line.startsWith("--") && !line.trim().isEmpty())
				.forEach(line -> {
					batch.add(line);
					if (batch.size() >= BATCH_SIZE) {
						jdbcTemplate.batchUpdate(batch.toArray(new String[0]));
						batch.clear();
					}
				});

			// left batch
			if (!batch.isEmpty()) {
				jdbcTemplate.batchUpdate(batch.toArray(new String[0]));
			}
		}
	}
}
