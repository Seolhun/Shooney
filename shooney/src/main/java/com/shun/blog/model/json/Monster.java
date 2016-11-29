package com.shun.blog.model.json;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "MONSTER")
public class Monster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "TYPE")
	@NotEmpty
	private String type;

	@Column(name = "SORTKEY")
	@NotEmpty
	private String sortKey;

	@Column(name = "NAME_KR")
	@NotEmpty
	private String name_kr;

	@Column(name = "NAME_JA")
	@NotEmpty
	private String name_ja;

	@Column(name = "NAME_EN")
	@NotEmpty
	private String name_en;

	@Column(name = "NAME_FR")
	@NotEmpty
	private String name_fr;

	@Column(name = "NAME_DE")
	@NotEmpty
	private String name_de;

	@Column(name = "DESCRIPTION_KR")
	@NotEmpty
	private String description_kr;

	@Column(name = "DESCRIPTION_JA")
	@NotEmpty
	private String description_ja;

	@Column(name = "DESCRIPTION_EN")
	@NotEmpty
	private String description_en;

	@Column(name = "DESCRIPTION_FR")
	@NotEmpty
	private String Description_fr;

	@Column(name = "DESCRIPTION_DE")
	@NotEmpty
	private String Description_de;

	@Column(name = "LEVELMAX")
	@NotEmpty
	private int levelMax;

	@Column(name = "LEVELMIN")
	@NotEmpty
	private int levelMin;

	@Column(name = "TIME")
	@NotEmpty
	private int Time;

	@Column(name = "HALFWAY")
	@NotEmpty
	private int Halfway;

	@Column(name = "RANDOMCONTENTTYPE")
	@NotEmpty
	private int RandomContentType;

	@Column(name = "ALLIANCE")
	@NotEmpty
	private int Alliance;

	@Column(name = "FINDERPARTYCONDITION")
	@NotEmpty
	private int FinderPartyCondition;

	@Column(name = "PARTYMEMBERCOUNT")
	@NotEmpty
	private int PartyMemberCount;

	@Column(name = "TANKCOUNT")
	@NotEmpty
	private int TankCount;

	@Column(name = "HEALERCOUNT")
	@NotEmpty
	private int HealerCount;

	@Column(name = "ATTACKERCOUNT")
	@NotEmpty
	private int AttackerCount;

	@Column(name = "RANGECOUNT")
	@NotEmpty
	private int RangeCount;

	@Column(name = "DIFFERENTIATEDPS")
	@NotEmpty
	private int DifferentiateDPS;

	@Column(name = "FREEROLE")
	@NotEmpty
	private int FreeRole;

	@Column(name = "ITEMLEVEL")
	@NotEmpty
	private int ItemLevel;

	@Column(name = "PARTYCOUNT")
	@NotEmpty
	private int PartyCount;

	@Column(name = "ITEMLEVELMAX")
	@NotEmpty
	private int ItemLevelMax;

	@Column(name = "FORCECOUNT")
	@NotEmpty
	private int ForceCount;

	@Column(name = "EXVERSION")
	@NotEmpty
	private int ExVersion;

	@Column(name = "IS_KOERU_USUALLY")
	@NotEmpty
	private int is_koeru_usually;

	@Column(name = "IS_KOERU_ANNIHILATION")
	@NotEmpty
	private int is_koeru_annihilation;

	@Column(name = "DATA")
	@NotEmpty
	private String data;

	@Column(name = "PATH")
	@NotEmpty
	private String path;

	@Column(name = "INDEX_KR")
	@NotEmpty
	private String Index_kr;

	@Column(name = "INDEX_JA")
	@NotEmpty
	private String Index_ja;

	@Column(name = "INDEX_EN")
	@NotEmpty
	private String Index_en;

	@Column(name = "INDEX_FR")
	@NotEmpty
	private String Index_fr;

	@Column(name = "INDEX_DE")
	@NotEmpty
	private String Index_de;

	@Column(name = "INDEX")
	@NotEmpty
	private String index;

	@Transient
	private ItemData itemData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getName_kr() {
		return name_kr;
	}

	public void setName_kr(String name_kr) {
		this.name_kr = name_kr;
	}

	public String getName_ja() {
		return name_ja;
	}

	public void setName_ja(String name_ja) {
		this.name_ja = name_ja;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_fr() {
		return name_fr;
	}

	public void setName_fr(String name_fr) {
		this.name_fr = name_fr;
	}

	public String getName_de() {
		return name_de;
	}

	public void setName_de(String name_de) {
		this.name_de = name_de;
	}

	public String getDescription_kr() {
		return description_kr;
	}

	public void setDescription_kr(String description_kr) {
		this.description_kr = description_kr;
	}

	public String getDescription_ja() {
		return description_ja;
	}

	public void setDescription_ja(String description_ja) {
		this.description_ja = description_ja;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public String getDescription_fr() {
		return Description_fr;
	}

	public void setDescription_fr(String description_fr) {
		Description_fr = description_fr;
	}

	public String getDescription_de() {
		return Description_de;
	}

	public void setDescription_de(String description_de) {
		Description_de = description_de;
	}

	public int getLevelMax() {
		return levelMax;
	}

	public void setLevelMax(int levelMax) {
		this.levelMax = levelMax;
	}

	public int getLevelMin() {
		return levelMin;
	}

	public void setLevelMin(int levelMin) {
		this.levelMin = levelMin;
	}

	public int getTime() {
		return Time;
	}

	public void setTime(int time) {
		Time = time;
	}

	public int getHalfway() {
		return Halfway;
	}

	public void setHalfway(int halfway) {
		Halfway = halfway;
	}

	public int getRandomContentType() {
		return RandomContentType;
	}

	public void setRandomContentType(int randomContentType) {
		RandomContentType = randomContentType;
	}

	public int getAlliance() {
		return Alliance;
	}

	public void setAlliance(int alliance) {
		Alliance = alliance;
	}

	public int getFinderPartyCondition() {
		return FinderPartyCondition;
	}

	public void setFinderPartyCondition(int finderPartyCondition) {
		FinderPartyCondition = finderPartyCondition;
	}

	public int getPartyMemberCount() {
		return PartyMemberCount;
	}

	public void setPartyMemberCount(int partyMemberCount) {
		PartyMemberCount = partyMemberCount;
	}

	public int getTankCount() {
		return TankCount;
	}

	public void setTankCount(int tankCount) {
		TankCount = tankCount;
	}

	public int getHealerCount() {
		return HealerCount;
	}

	public void setHealerCount(int healerCount) {
		HealerCount = healerCount;
	}

	public int getAttackerCount() {
		return AttackerCount;
	}

	public void setAttackerCount(int attackerCount) {
		AttackerCount = attackerCount;
	}

	public int getRangeCount() {
		return RangeCount;
	}

	public void setRangeCount(int rangeCount) {
		RangeCount = rangeCount;
	}

	public int getDifferentiateDPS() {
		return DifferentiateDPS;
	}

	public void setDifferentiateDPS(int differentiateDPS) {
		DifferentiateDPS = differentiateDPS;
	}

	public int getFreeRole() {
		return FreeRole;
	}

	public void setFreeRole(int freeRole) {
		FreeRole = freeRole;
	}

	public int getItemLevel() {
		return ItemLevel;
	}

	public void setItemLevel(int itemLevel) {
		ItemLevel = itemLevel;
	}

	public int getPartyCount() {
		return PartyCount;
	}

	public void setPartyCount(int partyCount) {
		PartyCount = partyCount;
	}

	public int getItemLevelMax() {
		return ItemLevelMax;
	}

	public void setItemLevelMax(int itemLevelMax) {
		ItemLevelMax = itemLevelMax;
	}

	public int getForceCount() {
		return ForceCount;
	}

	public void setForceCount(int forceCount) {
		ForceCount = forceCount;
	}

	public int getExVersion() {
		return ExVersion;
	}

	public void setExVersion(int exVersion) {
		ExVersion = exVersion;
	}

	public int getIs_koeru_usually() {
		return is_koeru_usually;
	}

	public void setIs_koeru_usually(int is_koeru_usually) {
		this.is_koeru_usually = is_koeru_usually;
	}

	public int getIs_koeru_annihilation() {
		return is_koeru_annihilation;
	}

	public void setIs_koeru_annihilation(int is_koeru_annihilation) {
		this.is_koeru_annihilation = is_koeru_annihilation;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIndex_kr() {
		return Index_kr;
	}

	public void setIndex_kr(String index_kr) {
		Index_kr = index_kr;
	}

	public String getIndex_ja() {
		return Index_ja;
	}

	public void setIndex_ja(String index_ja) {
		Index_ja = index_ja;
	}

	public String getIndex_en() {
		return Index_en;
	}

	public void setIndex_en(String index_en) {
		Index_en = index_en;
	}

	public String getIndex_fr() {
		return Index_fr;
	}

	public void setIndex_fr(String index_fr) {
		Index_fr = index_fr;
	}

	public String getIndex_de() {
		return Index_de;
	}

	public void setIndex_de(String index_de) {
		Index_de = index_de;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ItemData getItemData() {
		return itemData;
	}

	public void setItemData(ItemData itemData) {
		this.itemData = itemData;
	}
}
