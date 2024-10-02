package sample.cafekiosk.spring.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 엔티티 클래스들이 상속받을 수 있는 공통 부모 클래스를 정의할 때 사용
@EntityListeners(AuditingEntityListener.class) // JPA 엔티티의 변경 사항을 감지하고, 이를 처리할 수 있도록 이벤트 리스너를 등록하는 데 사용되는 애너테이션
public class BaseEntity {

    // 엔티티가 자동으로 생성되거나 변경됐을 때마다 여기에 시간이 찍힘

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;
}


