package com.gofit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "activitylog")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_log_id")
    private int activityLogId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                                                  CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "session_id")
    private Session session;


}
