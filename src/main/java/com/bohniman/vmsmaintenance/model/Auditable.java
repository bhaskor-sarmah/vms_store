package com.bohniman.vmsmaintenance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Auditable {

	@CreatedBy
	@Column(nullable = true, updatable = false)
	@Size(max = 80)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, updatable = false)
	@CreatedDate
	private Date createdAt;

	@LastModifiedBy
	@Size(max = 80)
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@LastModifiedDate
	private Date updatedAt;

	
}