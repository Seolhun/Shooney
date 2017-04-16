package com.shun.blog.model.menu;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity(name="TB_MENU")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_ID")
	private Long menuId;
	
	@Column(name = "MENU_NAME", nullable = false, length=30)
	private String menuName;
	
	//admin , index 등 다양한 종류.
	@Column(name = "MENU_TYPE", length=30, nullable = false)
	private String menuType;

	@Column(name = "MENU_URL", length=80, nullable = false)
	private String menuUrl;
	
	@Column(name = "MENU_ORDER", length=10, nullable = false)
	private int menuOrder;
	
	@Column(name = "MENU_DEPTH", length=10, nullable = false)
	private int menuDepth;
	
	@Column(name = "MENU_PARENT_ID", length=10, nullable = true)
	private Long menuParentId;
	
	@Column(name = "MENU_CREATED_BY", length = 60)
	private String createdBy;

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
