package org.example.exchangeoffice.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_operations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExchangeOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "currency_id", nullable = false)
    private Long currencyId;

    // Добавляем поле для столбца currency
    @Column(name = "currency", nullable = false)
    private Long currency;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal rate;

    @Column(name = "exchanged_amount", nullable = false)
    private BigDecimal exchangedAmount;

    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }
}