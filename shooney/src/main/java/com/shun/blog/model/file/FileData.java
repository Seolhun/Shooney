package com.shun.blog.model.file;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import com.shun.blog.model.board.Board;

@Entity
@Table(name = "TB_FILEDATA")
public class FileData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "FILENAME", length=100, nullable = false)
	@NotEmpty
	private String fileName;

	@Column(name = "FILEPATH", nullable = false)
	@NotEmpty
	private String filePath;

	@Column(name = "DESCRIPTION", length=255, nullable = false)
	@NotEmpty
	private String description;

	@Column(name = "TYPE", length=100, nullable = false)
	@NotEmpty
	private String type;
	
	@Column(name = "LATESTDATE", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date latestDate;
	
	@Lob 
	@Basic(fetch = FetchType.LAZY)
    @Column(name="CONTENT", nullable=false)
    private byte[] content;

	@ManyToOne(optional = false)
    @JoinColumn(name = "BOARDID")
    private Board board;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Date getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	

	
//	@Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
//        result = prime * result + ((name == null) ? 0 : name.hashCode());
//        return result;
//    }
// 
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (!(obj instanceof UserDocument))
//            return false;
//        UserDocument other = (UserDocument) obj;
//        if (id == null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.id))
//            return false;
//        if (name == null) {
//            if (other.name != null)
//                return false;
//        } else if (!name.equals(other.name))
//            return false;
//        return true;
//    }
// 
//    @Override
//    public String toString() {
//        return "UserDocument [id=" + id + ", name=" + name + ", description="
//                + description + ", type=" + type + "]";
//    }
// 
}
