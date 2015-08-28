
    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[fk_CommonCode_ParentCommonCode]') AND parent_object_id = OBJECT_ID('CommonCodes'))
alter table CommonCodes  drop constraint fk_CommonCode_ParentCommonCode


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[fk_Department_ParentDepartment]') AND parent_object_id = OBJECT_ID('Departments'))
alter table Departments  drop constraint fk_Department_ParentDepartment


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKB32FAD82B6031B8B]') AND parent_object_id = OBJECT_ID('SysLogs'))
alter table SysLogs  drop constraint FKB32FAD82B6031B8B


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKD3FB4CDE431EAFFD]') AND parent_object_id = OBJECT_ID('Tasks'))
alter table Tasks  drop constraint FKD3FB4CDE431EAFFD


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKB5A49C0D70F4C9D]') AND parent_object_id = OBJECT_ID('TaskAsks'))
alter table TaskAsks  drop constraint FKB5A49C0D70F4C9D


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKB5A49C0D431EAFFD]') AND parent_object_id = OBJECT_ID('TaskAsks'))
alter table TaskAsks  drop constraint FKB5A49C0D431EAFFD


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKEC6001B170F4C9D]') AND parent_object_id = OBJECT_ID('TaskResponds'))
alter table TaskResponds  drop constraint FKEC6001B170F4C9D


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKEC6001B1431EAFFD]') AND parent_object_id = OBJECT_ID('TaskResponds'))
alter table TaskResponds  drop constraint FKEC6001B1431EAFFD


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK197152C51F1A51B7]') AND parent_object_id = OBJECT_ID('UserEvals'))
alter table UserEvals  drop constraint FK197152C51F1A51B7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK197152C51B4612F8]') AND parent_object_id = OBJECT_ID('UserEvals'))
alter table UserEvals  drop constraint FK197152C51B4612F8


    if exists (select * from dbo.sysobjects where id = object_id(N'CommonCodes') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table CommonCodes

    if exists (select * from dbo.sysobjects where id = object_id(N'Departments') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Departments

    if exists (select * from dbo.sysobjects where id = object_id(N'Managers') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Managers

    if exists (select * from dbo.sysobjects where id = object_id(N'SysLogs') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table SysLogs

    if exists (select * from dbo.sysobjects where id = object_id(N'Tasks') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Tasks

    if exists (select * from dbo.sysobjects where id = object_id(N'TaskAsks') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table TaskAsks

    if exists (select * from dbo.sysobjects where id = object_id(N'TaskResponds') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table TaskResponds

    if exists (select * from dbo.sysobjects where id = object_id(N'Users') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Users

    if exists (select * from dbo.sysobjects where id = object_id(N'UserEvals') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table UserEvals

    create table CommonCodes (
        Id INT IDENTITY NOT NULL,
       Type INT null,
       Name NVARCHAR(255) null,
       Param NVARCHAR(255) null,
       Note NVARCHAR(255) null,
       ParentFk INT null,
       primary key (Id)
    )

    create table Departments (
        Id INT IDENTITY NOT NULL,
       Name NVARCHAR(255) null,
       Level INT null,
       Note NVARCHAR(255) null,
       ParentFk INT null,
       primary key (Id)
    )

    create table Managers (
        Id INT IDENTITY NOT NULL,
       LoginName NVARCHAR(255) null,
       MemberShipId UNIQUEIDENTIFIER null,
       RealName NVARCHAR(255) null,
       NickName NVARCHAR(255) null,
       Gender INT null,
       Mobile NVARCHAR(255) null,
       Enabled INT null,
       Note NVARCHAR(255) null,
       CreateTime DATETIME null,
       primary key (Id)
    )

    create table SysLogs (
        Id INT IDENTITY NOT NULL,
       LogInfo NVARCHAR(255) null,
       CreateTime DATETIME null,
       ManagerFk INT null,
       primary key (Id)
    )

    create table Tasks (
        Id INT IDENTITY NOT NULL,
       Content NVARCHAR(255) null,
       Status INT null,
       Duration INT null,
       CreateTime DATETIME null,
       UserFk INT null,
       primary key (Id)
    )

    create table TaskAsks (
        Id INT IDENTITY NOT NULL,
       Content NVARCHAR(255) null,
       CreateTime DATETIME null,
       TaskFk INT null,
       UserFk INT null,
       primary key (Id)
    )

    create table TaskResponds (
        Id INT IDENTITY NOT NULL,
       AuditReason NVARCHAR(255) null,
       AuditTime DATETIME null,
       Status INT null,
       CreateTime DATETIME null,
       TaskFk INT null,
       UserFk INT null,
       primary key (Id)
    )

    create table Users (
        Id INT IDENTITY NOT NULL,
       LoginName NVARCHAR(255) null,
       MemberShipId UNIQUEIDENTIFIER null,
       RealName NVARCHAR(255) null,
       NickName NVARCHAR(255) null,
       Gender INT null,
       Mobile NVARCHAR(255) null,
       Level INT null,
       Points INT null,
       CallTimes INT null,
       RespondTimes INT null,
       ReceiveTimes INT null,
       Intro NVARCHAR(255) null,
       Enabled INT null,
       Note NVARCHAR(255) null,
       CreateTime DATETIME null,
       primary key (Id)
    )

    create table UserEvals (
        Id INT IDENTITY NOT NULL,
       UserRole INT null,
       Content NVARCHAR(255) null,
       Level INT null,
       CreateTime DATETIME null,
       ToUserFk INT null,
       FromUserFk INT null,
       primary key (Id)
    )

    alter table CommonCodes 
        add constraint fk_CommonCode_ParentCommonCode 
        foreign key (ParentFk) 
        references CommonCodes

    alter table Departments 
        add constraint fk_Department_ParentDepartment 
        foreign key (ParentFk) 
        references Departments

    alter table SysLogs 
        add constraint FKB32FAD82B6031B8B 
        foreign key (ManagerFk) 
        references Managers

    alter table Tasks 
        add constraint FKD3FB4CDE431EAFFD 
        foreign key (UserFk) 
        references Users

    alter table TaskAsks 
        add constraint FKB5A49C0D70F4C9D 
        foreign key (TaskFk) 
        references Tasks

    alter table TaskAsks 
        add constraint FKB5A49C0D431EAFFD 
        foreign key (UserFk) 
        references Users

    alter table TaskResponds 
        add constraint FKEC6001B170F4C9D 
        foreign key (TaskFk) 
        references Tasks

    alter table TaskResponds 
        add constraint FKEC6001B1431EAFFD 
        foreign key (UserFk) 
        references Users

    alter table UserEvals 
        add constraint FK197152C51F1A51B7 
        foreign key (ToUserFk) 
        references Users

    alter table UserEvals 
        add constraint FK197152C51B4612F8 
        foreign key (FromUserFk) 
        references Users
