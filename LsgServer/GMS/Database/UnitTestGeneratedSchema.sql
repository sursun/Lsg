
    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[fk_CommonCode_ParentCommonCode]') AND parent_object_id = OBJECT_ID('CommonCodes'))
alter table CommonCodes  drop constraint fk_CommonCode_ParentCommonCode


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[fk_Department_ParentDepartment]') AND parent_object_id = OBJECT_ID('Departments'))
alter table Departments  drop constraint fk_Department_ParentDepartment


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK9200792C4EA6E759]') AND parent_object_id = OBJECT_ID('SysLogs'))
alter table SysLogs  drop constraint FK9200792C4EA6E759


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKB0CBFE5C432377B7]') AND parent_object_id = OBJECT_ID('Tasks'))
alter table Tasks  drop constraint FKB0CBFE5C432377B7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKAD636857320112A7]') AND parent_object_id = OBJECT_ID('TaskApplies'))
alter table TaskApplies  drop constraint FKAD636857320112A7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKAD636857432377B7]') AND parent_object_id = OBJECT_ID('TaskApplies'))
alter table TaskApplies  drop constraint FKAD636857432377B7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK592CE993320112A7]') AND parent_object_id = OBJECT_ID('TaskAsks'))
alter table TaskAsks  drop constraint FK592CE993320112A7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK592CE993432377B7]') AND parent_object_id = OBJECT_ID('TaskAsks'))
alter table TaskAsks  drop constraint FK592CE993432377B7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKB8E183B8320112A7]') AND parent_object_id = OBJECT_ID('UserEvals'))
alter table UserEvals  drop constraint FKB8E183B8320112A7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKB8E183B8106DC02E]') AND parent_object_id = OBJECT_ID('UserEvals'))
alter table UserEvals  drop constraint FKB8E183B8106DC02E


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKB8E183B8F93203E3]') AND parent_object_id = OBJECT_ID('UserEvals'))
alter table UserEvals  drop constraint FKB8E183B8F93203E3


    if exists (select * from dbo.sysobjects where id = object_id(N'CommonCodes') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table CommonCodes

    if exists (select * from dbo.sysobjects where id = object_id(N'Departments') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Departments

    if exists (select * from dbo.sysobjects where id = object_id(N'Managers') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Managers

    if exists (select * from dbo.sysobjects where id = object_id(N'SysLogs') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table SysLogs

    if exists (select * from dbo.sysobjects where id = object_id(N'Tasks') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Tasks

    if exists (select * from dbo.sysobjects where id = object_id(N'TaskApplies') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table TaskApplies

    if exists (select * from dbo.sysobjects where id = object_id(N'TaskAsks') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table TaskAsks

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

    create table TaskApplies (
        Id INT IDENTITY NOT NULL,
       AuditReason NVARCHAR(255) null,
       AuditTime DATETIME null,
       Status INT null,
       CreateTime DATETIME null,
       TaskFk INT null,
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
       TaskFk INT null,
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
        add constraint FK9200792C4EA6E759 
        foreign key (ManagerFk) 
        references Managers

    alter table Tasks 
        add constraint FKB0CBFE5C432377B7 
        foreign key (UserFk) 
        references Users

    alter table TaskApplies 
        add constraint FKAD636857320112A7 
        foreign key (TaskFk) 
        references Tasks

    alter table TaskApplies 
        add constraint FKAD636857432377B7 
        foreign key (UserFk) 
        references Users

    alter table TaskAsks 
        add constraint FK592CE993320112A7 
        foreign key (TaskFk) 
        references Tasks

    alter table TaskAsks 
        add constraint FK592CE993432377B7 
        foreign key (UserFk) 
        references Users

    alter table UserEvals 
        add constraint FKB8E183B8320112A7 
        foreign key (TaskFk) 
        references Tasks

    alter table UserEvals 
        add constraint FKB8E183B8106DC02E 
        foreign key (ToUserFk) 
        references Users

    alter table UserEvals 
        add constraint FKB8E183B8F93203E3 
        foreign key (FromUserFk) 
        references Users
