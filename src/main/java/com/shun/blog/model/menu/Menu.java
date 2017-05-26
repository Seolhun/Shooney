package com.shun.blog.model.menu;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity(name="TB_MENU")
public class Menu implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_ID")
	private Long menuId;
	
	@Column(name = "MENU_NAME", length=30, nullable = false)
	private String menuName;
	
	//admin , index 등 다양한 종류.
	@Column(name = "MENU_TYPE", length=30, nullable = false)
	private String menuType;

	@Column(name = "MENU_URL", length=80)
	private String menuUrl;
	
	@Column(name = "MENU_ORDER", length=10, nullable = false)
	private int menuOrder;
	
	@Column(name = "MENU_DEPTH", length=10, nullable = false)
	private int menuDepth;
	
	@Column(name = "MENU_PARENT_ID", length=10, nullable = true)
	private Long menuParentId;
	
	@Column(name = "MENU_CREATED_BY", length = 60)
	private String createdBy;
	
	@Column(name = "MENU_DEL_FLAG", length = 1)
	private String delFlag;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MENU_CREATED_DATE")
	private Date createdDate;
	
	@Transient
	private int type;
	
	@Transient
	private List<Menu> submenuList;

    public Menu(){
		
	}
	
	public Menu(int menuDepth){
		this.menuDepth=menuDepth;
	}
	
	public Menu(int menuDepth, String menuType){
		this.menuDepth=menuDepth;
		this.menuType=menuType;
	}
}
