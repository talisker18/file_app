package general;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class CustomFile {
	
	private BasicFileAttributes attr;
	private String fileName;
	private File file;
	
	public CustomFile(File file) throws IOException {
		
		this.fileName = file.getName();
		this.file = file;
		
		Path filePath = this.file.toPath();
		this.attr = Files.readAttributes(filePath, BasicFileAttributes.class);
	}
	
	public BasicFileAttributes getBasicFileAttributes() {
		return this.attr;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public FileTime getFileDate() {
		return this.attr.creationTime();
	}

}