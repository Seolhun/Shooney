package com.shun.blog.model.stack;

import com.shun.blog.model.common.AbstractCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_STACK_IMG")
public class StackFile extends AbstractCommon implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IMG_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "STACK_IMG_FK"), name = "STACK_IMG_ID", referencedColumnName = "STACK_ID")
	private Stack stackInFile;

	@Column(name = "STACK_IMG_ORIGIN_NAME", length = 100)
	private String originName;

	@Column(name = "STACK_IMG_SAVED_NAME", length = 200)
	private String savedName;

	@Column(name = "STACK_IMG_SAVED_PATH", length = 200)
	private String savedPath;

	@Column(name = " STACK_IMG_TYPE", length = 20)
	private String type;
	
	@Column(name = " STACK_IMG_SIZE", length = 20)
	private Long size;
	
	@Column(name = "STACK_IMG_CREATED_BY", length = 60)
	private String createdBy;
}
