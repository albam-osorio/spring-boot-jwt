package co.gov.sic.app.polls.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import co.gov.sic.core.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "brands")
@DynamicUpdate
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Brand extends BaseEntity<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Setter(value = AccessLevel.PROTECTED)
	private Long id;
	
	@Column(length = 50, nullable = false)
	public String code;
	
	@Column(length = 50, nullable = false)
	public String name;
}
