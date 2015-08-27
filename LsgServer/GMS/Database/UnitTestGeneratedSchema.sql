
    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[fk_CommonCode_ParentCommonCode]') AND parent_object_id = OBJECT_ID('CommonCodes'))
alter table CommonCodes  drop constraint fk_CommonCode_ParentCommonCode


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[fk_Department_ParentDepartment]') AND parent_object_id = OBJECT_ID('Departments'))
alter table Departments  drop constraint fk_Department_ParentDepartment


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF77128E3C32C267D]') AND parent_object_id = OBJECT_ID('Diseases'))
alter table Diseases  drop constraint FKF77128E3C32C267D


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKE7C6751AF8351AF9]') AND parent_object_id = OBJECT_ID('Doctors'))
alter table Doctors  drop constraint FKE7C6751AF8351AF9


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A7247DA46D749]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A7247DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A72471EDB719F]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A72471EDB719F


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A72473CFA7D55]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A72473CFA7D55


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A7247CA32E3DB]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A7247CA32E3DB


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A7247F7C41F28]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A7247F7C41F28


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A72478F6CE742]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A72478F6CE742


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A7247699847EE]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A7247699847EE


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A724793372D32]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A724793372D32


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A724740CE99B4]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A724740CE99B4


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A7247F9A0D44C]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A7247F9A0D44C


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A7247B5B00CF0]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A7247B5B00CF0


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A72473D28C322]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A72473D28C322


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A72478BC18947]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A72478BC18947


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A72475A9D47FF]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A72475A9D47FF


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A7247D6D25AF7]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A7247D6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK553A72473E0B8896]') AND parent_object_id = OBJECT_ID('FollowUp_BaseLines'))
alter table FollowUp_BaseLines  drop constraint FK553A72473E0B8896


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK6D2245B0DA46D749]') AND parent_object_id = OBJECT_ID('FollowUp_FollowUpInfos'))
alter table FollowUp_FollowUpInfos  drop constraint FK6D2245B0DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK6D2245B0D6D25AF7]') AND parent_object_id = OBJECT_ID('FollowUp_FollowUpInfos'))
alter table FollowUp_FollowUpInfos  drop constraint FK6D2245B0D6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF380ED24DA46D749]') AND parent_object_id = OBJECT_ID('FollowUp_GdBaseLines'))
alter table FollowUp_GdBaseLines  drop constraint FKF380ED24DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF380ED24AB662E35]') AND parent_object_id = OBJECT_ID('FollowUp_GdBaseLines'))
alter table FollowUp_GdBaseLines  drop constraint FKF380ED24AB662E35


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF380ED2460875523]') AND parent_object_id = OBJECT_ID('FollowUp_GdBaseLines'))
alter table FollowUp_GdBaseLines  drop constraint FKF380ED2460875523


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF380ED241E097907]') AND parent_object_id = OBJECT_ID('FollowUp_GdBaseLines'))
alter table FollowUp_GdBaseLines  drop constraint FKF380ED241E097907


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF380ED24382AE1BC]') AND parent_object_id = OBJECT_ID('FollowUp_GdBaseLines'))
alter table FollowUp_GdBaseLines  drop constraint FKF380ED24382AE1BC


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF380ED2493372D32]') AND parent_object_id = OBJECT_ID('FollowUp_GdBaseLines'))
alter table FollowUp_GdBaseLines  drop constraint FKF380ED2493372D32


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF380ED245A9D47FF]') AND parent_object_id = OBJECT_ID('FollowUp_GdBaseLines'))
alter table FollowUp_GdBaseLines  drop constraint FKF380ED245A9D47FF


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF380ED24D6D25AF7]') AND parent_object_id = OBJECT_ID('FollowUp_GdBaseLines'))
alter table FollowUp_GdBaseLines  drop constraint FKF380ED24D6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF380ED243E0B8896]') AND parent_object_id = OBJECT_ID('FollowUp_GdBaseLines'))
alter table FollowUp_GdBaseLines  drop constraint FKF380ED243E0B8896


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF73FB54ADA46D749]') AND parent_object_id = OBJECT_ID('Health_Clinics'))
alter table Health_Clinics  drop constraint FKF73FB54ADA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK9E18A22DA46D749]') AND parent_object_id = OBJECT_ID('Health_Educations'))
alter table Health_Educations  drop constraint FK9E18A22DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKFC73D884DA46D749]') AND parent_object_id = OBJECT_ID('Health_EvaluationScales'))
alter table Health_EvaluationScales  drop constraint FKFC73D884DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK39325254DA46D749]') AND parent_object_id = OBJECT_ID('Health_HealthEducations'))
alter table Health_HealthEducations  drop constraint FK39325254DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF7D05A583CFA7D55]') AND parent_object_id = OBJECT_ID('Health_MedicalHistoryItems'))
alter table Health_MedicalHistoryItems  drop constraint FKF7D05A583CFA7D55


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKFC02C71C72964841]') AND parent_object_id = OBJECT_ID('Health_Medicates'))
alter table Health_Medicates  drop constraint FKFC02C71C72964841


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKFC02C71C5A9D47FF]') AND parent_object_id = OBJECT_ID('Health_Medicates'))
alter table Health_Medicates  drop constraint FKFC02C71C5A9D47FF


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK6E98B8C6DA46D749]') AND parent_object_id = OBJECT_ID('LeaveWords'))
alter table LeaveWords  drop constraint FK6E98B8C6DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK6E98B8C6D6D25AF7]') AND parent_object_id = OBJECT_ID('LeaveWords'))
alter table LeaveWords  drop constraint FK6E98B8C6D6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKE12568EBABD2AA01]') AND parent_object_id = OBJECT_ID('Medicines'))
alter table Medicines  drop constraint FKE12568EBABD2AA01


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK56BD950DE1C339DF]') AND parent_object_id = OBJECT_ID('Notices'))
alter table Notices  drop constraint FK56BD950DE1C339DF


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK56BD950DD6D25AF7]') AND parent_object_id = OBJECT_ID('Notices'))
alter table Notices  drop constraint FK56BD950DD6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKB4C281C0490E7C04]') AND parent_object_id = OBJECT_ID('Patients'))
alter table Patients  drop constraint FKB4C281C0490E7C04


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKB4C281C095CA8F8C]') AND parent_object_id = OBJECT_ID('Patients'))
alter table Patients  drop constraint FKB4C281C095CA8F8C


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKB4C281C0D6D25AF7]') AND parent_object_id = OBJECT_ID('Patients'))
alter table Patients  drop constraint FKB4C281C0D6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK9200792CD6D25AF7]') AND parent_object_id = OBJECT_ID('SysLogs'))
alter table SysLogs  drop constraint FK9200792CD6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E89F7C41F28]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E89F7C41F28


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E898F6CE742]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E898F6CE742


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E89699847EE]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E89699847EE


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E8993372D32]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E8993372D32


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E89F9A0D44C]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E89F9A0D44C


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E89B5B00CF0]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E89B5B00CF0


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E893D28C322]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E893D28C322


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E898BC18947]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E898BC18947


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E895A9D47FF]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E895A9D47FF


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E89DA46D749]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E89DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E8940CE99B4]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E8940CE99B4


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E89D6D25AF7]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E89D6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK63FF7E893E0B8896]') AND parent_object_id = OBJECT_ID('FollowUp_Annuals'))
alter table FollowUp_Annuals  drop constraint FK63FF7E893E0B8896


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK129D843093372D32]') AND parent_object_id = OBJECT_ID('FollowUp_FuZhens'))
alter table FollowUp_FuZhens  drop constraint FK129D843093372D32


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK129D84305A9D47FF]') AND parent_object_id = OBJECT_ID('FollowUp_FuZhens'))
alter table FollowUp_FuZhens  drop constraint FK129D84305A9D47FF


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK129D8430DA46D749]') AND parent_object_id = OBJECT_ID('FollowUp_FuZhens'))
alter table FollowUp_FuZhens  drop constraint FK129D8430DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK129D843040CE99B4]') AND parent_object_id = OBJECT_ID('FollowUp_FuZhens'))
alter table FollowUp_FuZhens  drop constraint FK129D843040CE99B4


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK129D8430D6D25AF7]') AND parent_object_id = OBJECT_ID('FollowUp_FuZhens'))
alter table FollowUp_FuZhens  drop constraint FK129D8430D6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK129D84303E0B8896]') AND parent_object_id = OBJECT_ID('FollowUp_FuZhens'))
alter table FollowUp_FuZhens  drop constraint FK129D84303E0B8896


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK720AA0C3DA46D749]') AND parent_object_id = OBJECT_ID('FollowUp_GdFollowUps'))
alter table FollowUp_GdFollowUps  drop constraint FK720AA0C3DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK720AA0C3D6D25AF7]') AND parent_object_id = OBJECT_ID('FollowUp_GdFollowUps'))
alter table FollowUp_GdFollowUps  drop constraint FK720AA0C3D6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK720AA0C33E0B8896]') AND parent_object_id = OBJECT_ID('FollowUp_GdFollowUps'))
alter table FollowUp_GdFollowUps  drop constraint FK720AA0C33E0B8896


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKD5A7BAA67CD1E4BB]') AND parent_object_id = OBJECT_ID('FollowUp_ChanHous'))
alter table FollowUp_ChanHous  drop constraint FKD5A7BAA67CD1E4BB


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKD5A7BAA695CA8F8C]') AND parent_object_id = OBJECT_ID('FollowUp_ChanHous'))
alter table FollowUp_ChanHous  drop constraint FKD5A7BAA695CA8F8C


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK3B130A3C7CD1E4BB]') AND parent_object_id = OBJECT_ID('FollowUp_YunZhongs'))
alter table FollowUp_YunZhongs  drop constraint FK3B130A3C7CD1E4BB


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK3B130A3C5A9D47FF]') AND parent_object_id = OBJECT_ID('FollowUp_YunZhongs'))
alter table FollowUp_YunZhongs  drop constraint FK3B130A3C5A9D47FF


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKD45386955A9D47FF]') AND parent_object_id = OBJECT_ID('FollowUp_TiaoZhengs'))
alter table FollowUp_TiaoZhengs  drop constraint FKD45386955A9D47FF


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKD4538695DA46D749]') AND parent_object_id = OBJECT_ID('FollowUp_TiaoZhengs'))
alter table FollowUp_TiaoZhengs  drop constraint FKD4538695DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKD453869540CE99B4]') AND parent_object_id = OBJECT_ID('FollowUp_TiaoZhengs'))
alter table FollowUp_TiaoZhengs  drop constraint FKD453869540CE99B4


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKD4538695D6D25AF7]') AND parent_object_id = OBJECT_ID('FollowUp_TiaoZhengs'))
alter table FollowUp_TiaoZhengs  drop constraint FKD4538695D6D25AF7


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKD45386953E0B8896]') AND parent_object_id = OBJECT_ID('FollowUp_TiaoZhengs'))
alter table FollowUp_TiaoZhengs  drop constraint FKD45386953E0B8896


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK8DFDE96EDA46D749]') AND parent_object_id = OBJECT_ID('Health_Diagnoses'))
alter table Health_Diagnoses  drop constraint FK8DFDE96EDA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK8DFDE96E95CA8F8C]') AND parent_object_id = OBJECT_ID('Health_Diagnoses'))
alter table Health_Diagnoses  drop constraint FK8DFDE96E95CA8F8C


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKDD19CCFC24756E05]') AND parent_object_id = OBJECT_ID('Health_Diagnoses_Diseases'))
alter table Health_Diagnoses_Diseases  drop constraint FKDD19CCFC24756E05


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKDD19CCFC8BC18947]') AND parent_object_id = OBJECT_ID('Health_Diagnoses_Diseases'))
alter table Health_Diagnoses_Diseases  drop constraint FKDD19CCFC8BC18947


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK859640FDDA46D749]') AND parent_object_id = OBJECT_ID('Health_FamilyHistories'))
alter table Health_FamilyHistories  drop constraint FK859640FDDA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKF160676ADA46D749]') AND parent_object_id = OBJECT_ID('Health_GdHistories'))
alter table Health_GdHistories  drop constraint FKF160676ADA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK749F796DDA46D749]') AND parent_object_id = OBJECT_ID('Health_GdmRisks'))
alter table Health_GdmRisks  drop constraint FK749F796DDA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK9A15E50CDA46D749]') AND parent_object_id = OBJECT_ID('Health_MedicalHistories'))
alter table Health_MedicalHistories  drop constraint FK9A15E50CDA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK3912AA74DA46D749]') AND parent_object_id = OBJECT_ID('Health_Treatments'))
alter table Health_Treatments  drop constraint FK3912AA74DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKE5140ED9DA46D749]') AND parent_object_id = OBJECT_ID('Examine_Bloods'))
alter table Examine_Bloods  drop constraint FKE5140ED9DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKCDB7983FDA46D749]') AND parent_object_id = OBJECT_ID('Examine_BloodRts'))
alter table Examine_BloodRts  drop constraint FKCDB7983FDA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK4FFE4744DA46D749]') AND parent_object_id = OBJECT_ID('Examine_Eyes'))
alter table Examine_Eyes  drop constraint FK4FFE4744DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK36CD1F8DDA46D749]') AND parent_object_id = OBJECT_ID('Examine_GdPhysicals'))
alter table Examine_GdPhysicals  drop constraint FK36CD1F8DDA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK52C85259DA46D749]') AND parent_object_id = OBJECT_ID('Examine_Legs'))
alter table Examine_Legs  drop constraint FK52C85259DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FK9CEABC28DA46D749]') AND parent_object_id = OBJECT_ID('Examine_Physicals'))
alter table Examine_Physicals  drop constraint FK9CEABC28DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKEA222FF1DA46D749]') AND parent_object_id = OBJECT_ID('Examine_TnbBasics'))
alter table Examine_TnbBasics  drop constraint FKEA222FF1DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKBB58FC1DDA46D749]') AND parent_object_id = OBJECT_ID('Examine_UnClassifieds'))
alter table Examine_UnClassifieds  drop constraint FKBB58FC1DDA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKD2C1AF4ADA46D749]') AND parent_object_id = OBJECT_ID('Examine_Uroscopies'))
alter table Examine_Uroscopies  drop constraint FKD2C1AF4ADA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKAA82317DDA46D749]') AND parent_object_id = OBJECT_ID('Health_GdIdentifications'))
alter table Health_GdIdentifications  drop constraint FKAA82317DDA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKAA82317DB81319C2]') AND parent_object_id = OBJECT_ID('Health_GdIdentifications'))
alter table Health_GdIdentifications  drop constraint FKAA82317DB81319C2


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKAA82317D8AB4E3CE]') AND parent_object_id = OBJECT_ID('Health_GdIdentifications'))
alter table Health_GdIdentifications  drop constraint FKAA82317D8AB4E3CE


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKAA82317D3685B55E]') AND parent_object_id = OBJECT_ID('Health_GdIdentifications'))
alter table Health_GdIdentifications  drop constraint FKAA82317D3685B55E


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKBE5BD5C0DA46D749]') AND parent_object_id = OBJECT_ID('Health_Identifications'))
alter table Health_Identifications  drop constraint FKBE5BD5C0DA46D749


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKBE5BD5C0B81319C2]') AND parent_object_id = OBJECT_ID('Health_Identifications'))
alter table Health_Identifications  drop constraint FKBE5BD5C0B81319C2


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKBE5BD5C08AB4E3CE]') AND parent_object_id = OBJECT_ID('Health_Identifications'))
alter table Health_Identifications  drop constraint FKBE5BD5C08AB4E3CE


    if exists (select 1 from sys.objects where object_id = OBJECT_ID(N'[FKBE5BD5C03685B55E]') AND parent_object_id = OBJECT_ID('Health_Identifications'))
alter table Health_Identifications  drop constraint FKBE5BD5C03685B55E


    if exists (select * from dbo.sysobjects where id = object_id(N'CommonCodes') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table CommonCodes

    if exists (select * from dbo.sysobjects where id = object_id(N'Departments') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Departments

    if exists (select * from dbo.sysobjects where id = object_id(N'Diseases') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Diseases

    if exists (select * from dbo.sysobjects where id = object_id(N'Doctors') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Doctors

    if exists (select * from dbo.sysobjects where id = object_id(N'FollowUp_BaseLines') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table FollowUp_BaseLines

    if exists (select * from dbo.sysobjects where id = object_id(N'FollowUp_FollowUpInfos') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table FollowUp_FollowUpInfos

    if exists (select * from dbo.sysobjects where id = object_id(N'FollowUp_GdBaseLines') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table FollowUp_GdBaseLines

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_Clinics') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_Clinics

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_Educations') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_Educations

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_EvaluationScales') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_EvaluationScales

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_HealthEducations') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_HealthEducations

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_MedicalHistoryItems') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_MedicalHistoryItems

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_Medicates') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_Medicates

    if exists (select * from dbo.sysobjects where id = object_id(N'LeaveWords') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table LeaveWords

    if exists (select * from dbo.sysobjects where id = object_id(N'Medicines') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Medicines

    if exists (select * from dbo.sysobjects where id = object_id(N'Notices') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Notices

    if exists (select * from dbo.sysobjects where id = object_id(N'Patients') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Patients

    if exists (select * from dbo.sysobjects where id = object_id(N'SysLogs') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table SysLogs

    if exists (select * from dbo.sysobjects where id = object_id(N'FollowUp_Annuals') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table FollowUp_Annuals

    if exists (select * from dbo.sysobjects where id = object_id(N'FollowUp_FuZhens') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table FollowUp_FuZhens

    if exists (select * from dbo.sysobjects where id = object_id(N'FollowUp_GdFollowUps') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table FollowUp_GdFollowUps

    if exists (select * from dbo.sysobjects where id = object_id(N'FollowUp_ChanHous') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table FollowUp_ChanHous

    if exists (select * from dbo.sysobjects where id = object_id(N'FollowUp_YunZhongs') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table FollowUp_YunZhongs

    if exists (select * from dbo.sysobjects where id = object_id(N'FollowUp_TiaoZhengs') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table FollowUp_TiaoZhengs

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_Diagnoses') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_Diagnoses

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_Diagnoses_Diseases') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_Diagnoses_Diseases

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_FamilyHistories') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_FamilyHistories

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_GdHistories') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_GdHistories

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_GdmRisks') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_GdmRisks

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_MedicalHistories') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_MedicalHistories

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_Treatments') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_Treatments

    if exists (select * from dbo.sysobjects where id = object_id(N'Examine_Bloods') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Examine_Bloods

    if exists (select * from dbo.sysobjects where id = object_id(N'Examine_BloodRts') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Examine_BloodRts

    if exists (select * from dbo.sysobjects where id = object_id(N'Examine_Eyes') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Examine_Eyes

    if exists (select * from dbo.sysobjects where id = object_id(N'Examine_GdPhysicals') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Examine_GdPhysicals

    if exists (select * from dbo.sysobjects where id = object_id(N'Examine_Legs') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Examine_Legs

    if exists (select * from dbo.sysobjects where id = object_id(N'Examine_Physicals') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Examine_Physicals

    if exists (select * from dbo.sysobjects where id = object_id(N'Examine_TnbBasics') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Examine_TnbBasics

    if exists (select * from dbo.sysobjects where id = object_id(N'Examine_UnClassifieds') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Examine_UnClassifieds

    if exists (select * from dbo.sysobjects where id = object_id(N'Examine_Uroscopies') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Examine_Uroscopies

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_GdIdentifications') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_GdIdentifications

    if exists (select * from dbo.sysobjects where id = object_id(N'Health_Identifications') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table Health_Identifications

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

    create table Diseases (
        Id INT IDENTITY NOT NULL,
       Name NVARCHAR(255) null,
       CodeNo NVARCHAR(255) null,
       PinYin NVARCHAR(255) null,
       TypeFk INT null,
       primary key (Id)
    )

    create table Doctors (
        Id INT IDENTITY NOT NULL,
       CodeNo NVARCHAR(255) null,
       ProfessionalLevel NVARCHAR(255) null,
       Duty NVARCHAR(255) null,
       LoginName NVARCHAR(255) null,
       MemberShipId UNIQUEIDENTIFIER null,
       RealName NVARCHAR(255) null,
       Sex INT null,
       Mobile NVARCHAR(255) null,
       ScopeType INT null,
       Scope NVARCHAR(255) null,
       Enabled INT null,
       Note NVARCHAR(255) null,
       CreateTime DATETIME null,
       DepartmentFk INT null,
       primary key (Id)
    )

    create table FollowUp_BaseLines (
        Id INT IDENTITY NOT NULL,
       Note NVARCHAR(255) null,
       CreateTime DATETIME null,
       PatientFk INT null,
       IdentificationFk INT null,
       MedicalHistoryFk INT null,
       FamilyHistoryFk INT null,
       PhysicalFk INT null,
       UroscopyFk INT null,
       BloodFk INT null,
       BloodRtFk INT null,
       TnbBasicFk INT null,
       EyeFk INT null,
       LegsFk INT null,
       UnClassifiedFk INT null,
       DiagnosesFk INT null,
       TreatmentFk INT null,
       DoctorFk INT null,
       PracticeDoctorFk INT null,
       primary key (Id)
    )

    create table FollowUp_FollowUpInfos (
        Id INT IDENTITY NOT NULL,
       FollowUpStatus INT null,
       FollowUpWay INT null,
       FollowUpDate DATETIME null,
       Note NVARCHAR(255) null,
       ModifyTime DATETIME null,
       PatientFk INT null,
       DoctorFk INT null,
       primary key (Id)
    )

    create table FollowUp_GdBaseLines (
        Id INT IDENTITY NOT NULL,
       Note NVARCHAR(255) null,
       CreateTime DATETIME null,
       PatientFk INT null,
       GdIdentificationFk INT null,
       GdHistoryFk INT null,
       GdmRiskFk INT null,
       GdPhysicalFk INT null,
       BloodRtFk INT null,
       TreatmentFk INT null,
       DoctorFk INT null,
       PracticeDoctorFk INT null,
       primary key (Id)
    )

    create table Health_Clinics (
        Id INT IDENTITY NOT NULL,
       Name NVARCHAR(255) null,
       IsBreathing INT null,
       DeathTime DATETIME null,
       DeathReason NVARCHAR(255) null,
       FollowUpStatus INT null,
       QingDxtTimes NVARCHAR(255) null,
       ZhongDxtTimes NVARCHAR(255) null,
       Dka BIT null,
       DkaDateTime DATETIME null,
       Hnkc BIT null,
       HnkcDateTime DATETIME null,
       RuShuanZd BIT null,
       RuShuanZdDateTime DATETIME null,
       XinJiaoTong BIT null,
       XinJiaoTongDateTime DATETIME null,
       XinJiGs BIT null,
       XinJiGsDateTime DATETIME null,
       XinShuai BIT null,
       XinShuaiDateTime DATETIME null,
       Cabg BIT null,
       CabgDateTime DATETIME null,
       XueGuanZt BIT null,
       XueGuanZtDateTime DATETIME null,
       Tia BIT null,
       TiaDateTime DATETIME null,
       NaoChuXue BIT null,
       NaoChuXueDateTime DATETIME null,
       NaoGengSe BIT null,
       NaoGengSeDateTime DATETIME null,
       ZhongLiu BIT null,
       ZhongLiuDateTime DATETIME null,
       ZhongLiuBuWei NVARCHAR(255) null,
       TouXi BIT null,
       TouXiDateTime DATETIME null,
       YiZhi BIT null,
       YiZhiDateTime DATETIME null,
       TnbZu BIT null,
       TnbZuDateTime DATETIME null,
       TnbZuPk BIT null,
       TnbZuPkDateTime DATETIME null,
       TnbZuKy BIT null,
       TnbZuKyDateTime DATETIME null,
       TnbShenBing BIT null,
       TnbShenBingDateTime DATETIME null,
       TnbSwm BIT null,
       TnbSwmDateTime DATETIME null,
       ShiMing BIT null,
       ShiMingDateTime DATETIME null,
       ShiLiJt BIT null,
       ShiLiJtDateTime DATETIME null,
       InHospital INT null,
       InHospitalDateTime DATETIME null,
       InHospitalReason NVARCHAR(255) null,
       CreateTime DATETIME null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Health_Educations (
        Id INT IDENTITY NOT NULL,
       EducationFlag INT null,
       Teacher NVARCHAR(255) null,
       CreateTime DATETIME null,
       YingYangShi INT null,
       HuShi INT null,
       ZuBing INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Health_EvaluationScales (
        Id INT IDENTITY NOT NULL,
       ScaleType INT null,
       ScaleName NVARCHAR(255) null,
       PaperName NVARCHAR(255) null,
       Result INT null,
       Answers NVARCHAR(255) null,
       CreateTime DATETIME null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Health_HealthEducations (
        Id INT IDENTITY NOT NULL,
       EducationFlag INT null,
       YingYangShi INT null,
       HuShi INT null,
       ZuBing INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Health_MedicalHistoryItems (
        Id INT IDENTITY NOT NULL,
       MedicalHistoryType INT null,
       Name NVARCHAR(255) null,
       Note NVARCHAR(255) null,
       DateTime DATETIME null,
       MedicalHistoryFk INT null,
       primary key (Id)
    )

    create table Health_Medicates (
        Id INT IDENTITY NOT NULL,
       Dosage NVARCHAR(255) null,
       Usage INT null,
       DoseType INT null,
       StartDateTime DATETIME null,
       Note NVARCHAR(255) null,
       MedicineFk INT null,
       TreatmentFk INT null,
       primary key (Id)
    )

    create table LeaveWords (
        Id INT IDENTITY NOT NULL,
       Title NVARCHAR(255) null,
       Content NVARCHAR(255) null,
       Age INT null,
       Sex INT null,
       Mobile NVARCHAR(255) null,
       CreateTime DATETIME null,
       Reply NVARCHAR(255) null,
       ReplyTime DATETIME null,
       Status INT null,
       PatientFk INT null,
       DoctorFk INT null,
       primary key (Id)
    )

    create table Medicines (
        Id INT IDENTITY NOT NULL,
       NormalName NVARCHAR(255) null,
       ChemicalName NVARCHAR(255) null,
       PinYin NVARCHAR(255) null,
       Model NVARCHAR(255) null,
       Recommend INT null,
       RecommendTime DATETIME null,
       Note NVARCHAR(255) null,
       Enabled INT null,
       MedicineTypeFk INT null,
       primary key (Id)
    )

    create table Notices (
        Id INT IDENTITY NOT NULL,
       Title NVARCHAR(255) null,
       Content NVARCHAR(255) null,
       CreateTime DATETIME null,
       ColumnTypeFk INT null,
       DoctorFk INT null,
       primary key (Id)
    )

    create table Patients (
        Id INT IDENTITY NOT NULL,
       CodeNo NVARCHAR(255) null,
       RealName NVARCHAR(255) null,
       Sex INT null,
       IdCard NVARCHAR(255) null,
       Birthday DATETIME null,
       Email NVARCHAR(255) null,
       Mobile1 NVARCHAR(255) null,
       Mobile2 NVARCHAR(255) null,
       DiabetesType INT null,
       DiagnoseDate DATETIME null,
       DiseaseStage INT null,
       Note NVARCHAR(255) null,
       CreateTime DATETIME null,
       AreaFk INT null,
       DiabetesFk INT null,
       DoctorFk INT null,
       primary key (Id)
    )

    create table SysLogs (
        Id INT IDENTITY NOT NULL,
       LogInfo NVARCHAR(255) null,
       CreateTime DATETIME null,
       DoctorFk INT null,
       primary key (Id)
    )

    create table FollowUp_Annuals (
        Id INT IDENTITY NOT NULL,
       EQ5D NVARCHAR(255) null,
       FollowUpType INT null,
       Name NVARCHAR(255) null,
       DiseaseStage INT null,
       Note NVARCHAR(255) null,
       CreateTime DATETIME null,
       ModifyTime DATETIME null,
       PhysicalFk INT null,
       UroscopyFk INT null,
       BloodFk INT null,
       BloodRtFk INT null,
       EyeFk INT null,
       LegsFk INT null,
       UnClassifiedFk INT null,
       DiagnosesFk INT null,
       TreatmentFk INT null,
       PatientFk INT null,
       TnbBasicFk INT null,
       DoctorFk INT null,
       PracticeDoctorFk INT null,
       primary key (Id)
    )

    create table FollowUp_FuZhens (
        Id INT IDENTITY NOT NULL,
       FollowUpType INT null,
       Name NVARCHAR(255) null,
       DiseaseStage INT null,
       Note NVARCHAR(255) null,
       CreateTime DATETIME null,
       ModifyTime DATETIME null,
       BloodRtFk INT null,
       TreatmentFk INT null,
       PatientFk INT null,
       TnbBasicFk INT null,
       DoctorFk INT null,
       PracticeDoctorFk INT null,
       primary key (Id)
    )

    create table FollowUp_GdFollowUps (
        Id INT IDENTITY NOT NULL,
       Name NVARCHAR(255) null,
       PregnancyWeeks NVARCHAR(255) null,
       Weight NVARCHAR(255) null,
       BloodPressureHigh NVARCHAR(255) null,
       BloodPressureLow NVARCHAR(255) null,
       Note NVARCHAR(255) null,
       CreateTime DATETIME null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       DoctorFk INT null,
       PracticeDoctorFk INT null,
       primary key (Id)
    )

    create table FollowUp_ChanHous (
        GdFollowUpFk INT not null,
       ChildbirthDateTime DATETIME null,
       BirthType INT null,
       ChuXueLiang NVARCHAR(255) null,
       BingFa INT null,
       BabyWeight NVARCHAR(255) null,
       Apgar NVARCHAR(255) null,
       BabyBing INT null,
       BabyQuexian NVARCHAR(255) null,
       Breathing INT null,
       DeathReason NVARCHAR(255) null,
       Dxt INT null,
       DxtValue NVARCHAR(255) null,
       BreastFeeding INT null,
       Glu0M NVARCHAR(255) null,
       Glu30M NVARCHAR(255) null,
       Glu120M NVARCHAR(255) null,
       Insulin0M NVARCHAR(255) null,
       Insulin30M NVARCHAR(255) null,
       Insulin120M NVARCHAR(255) null,
       Gad INT null,
       Ica INT null,
       Iaa INT null,
       DiabetesFk INT null,
       primary key (GdFollowUpFk)
    )

    create table FollowUp_YunZhongs (
        GdFollowUpFk INT not null,
       Ketone INT null,
       PbgHours NVARCHAR(255) null,
       PbgValue NVARCHAR(255) null,
       BloodCheckTimes NVARCHAR(255) null,
       Fbg NVARCHAR(255) null,
       Pbg0 NVARCHAR(255) null,
       Pbg1 NVARCHAR(255) null,
       Pbg2 NVARCHAR(255) null,
       DxtTimes INT null,
       HbA1c NVARCHAR(255) null,
       ALT NVARCHAR(255) null,
       GGT NVARCHAR(255) null,
       BUN NVARCHAR(255) null,
       UrineProtein INT null,
       TC NVARCHAR(255) null,
       TG NVARCHAR(255) null,
       HDL NVARCHAR(255) null,
       LDL NVARCHAR(255) null,
       Hb NVARCHAR(255) null,
       TreatmentFk INT null,
       primary key (GdFollowUpFk)
    )

    create table FollowUp_TiaoZhengs (
        Id INT IDENTITY NOT NULL,
       BloodPressureHigh NVARCHAR(255) null,
       BloodPressureLow NVARCHAR(255) null,
       FBG NVARCHAR(255) null,
       PBG NVARCHAR(255) null,
       HbA1c NVARCHAR(255) null,
       LDL NVARCHAR(255) null,
       Weight NVARCHAR(255) null,
       QingDxtTimes NVARCHAR(255) null,
       ZhongDxtTimes NVARCHAR(255) null,
       DxtTimes NVARCHAR(255) null,
       BloodCheckFlag INT null,
       BloodCheckInfo NVARCHAR(255) null,
       FollowUpType INT null,
       Name NVARCHAR(255) null,
       DiseaseStage INT null,
       Note NVARCHAR(255) null,
       CreateTime DATETIME null,
       ModifyTime DATETIME null,
       TreatmentFk INT null,
       PatientFk INT null,
       TnbBasicFk INT null,
       DoctorFk INT null,
       PracticeDoctorFk INT null,
       primary key (Id)
    )

    create table Health_Diagnoses (
        Id INT IDENTITY NOT NULL,
       DiseaseStage INT null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       DiabetesFk INT null,
       primary key (Id)
    )

    create table Health_Diagnoses_Diseases (
        DiagnosesFk INT not null,
       DiseaseFk INT not null
    )

    create table Health_FamilyHistories (
        Id INT IDENTITY NOT NULL,
       Father INT null,
       Mother INT null,
       Sibling NVARCHAR(255) null,
       SiblingSick NVARCHAR(255) null,
       Children NVARCHAR(255) null,
       ChildrenSick NVARCHAR(255) null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Health_GdHistories (
        Id INT IDENTITY NOT NULL,
       IllnessFlag INT null,
       SiTai NVARCHAR(255) null,
       SiChan NVARCHAR(255) null,
       LiuChan NVARCHAR(255) null,
       ZuYueChan NVARCHAR(255) null,
       ZaoChan NVARCHAR(255) null,
       GdSyndromeFlag INT null,
       GdEver INT null,
       GdmLevel INT null,
       FamilyIllnessFlag INT null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Health_GdmRisks (
        Id INT IDENTITY NOT NULL,
       HighRiskFlag INT null,
       WeightBefore NVARCHAR(255) null,
       Height NVARCHAR(255) null,
       Bmi NVARCHAR(255) null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Health_MedicalHistories (
        Id INT IDENTITY NOT NULL,
       GuanXinBing INT null,
       GuanXinBingCourse NVARCHAR(255) null,
       GuanXinBingDateTime DATETIME null,
       XinJiaoTong INT null,
       XinJiGs INT null,
       XueGuanZt INT null,
       GuanMaiDq INT null,
       XinShuai INT null,
       GaoXueYa INT null,
       GaoXueYaDateTime DATETIME null,
       GaoXueYaCourse NVARCHAR(255) null,
       NaoChuXue INT null,
       NaoGengSe INT null,
       GaoNiaoSxz INT null,
       TongFengGjy INT null,
       TongFengSb INT null,
       ShenXiaoQy INT null,
       DanBaiNiao INT null,
       DanBaiNiaoDateTime DATETIME null,
       XueYeTx INT null,
       XueYeTxDateTime DATETIME null,
       FuTouZl INT null,
       FuTouZlDateTime DATETIME null,
       ZhongLiu INT null,
       ShouShu INT null,
       GuZhe INT null,
       HuaiGuanjShang INT null,
       HuaiGuanjXia INT null,
       XueGuanZl INT null,
       YanDiBb INT null,
       ShenJingBb INT null,
       TnbZu INT null,
       TnbShenBing INT null,
       TnbTongZd INT null,
       FeiTongZhz INT null,
       RuShuanZd INT null,
       QingDxtTimes NVARCHAR(255) null,
       ZhongDxtTimes NVARCHAR(255) null,
       BoXing INT null,
       YangWei INT null,
       MaMu INT null,
       ShiWuMh INT null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Health_Treatments (
        Id INT IDENTITY NOT NULL,
       Other NVARCHAR(255) null,
       Sport NVARCHAR(255) null,
       Note NVARCHAR(255) null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Examine_Bloods (
        Id INT IDENTITY NOT NULL,
       Hb NVARCHAR(255) null,
       HCT NVARCHAR(255) null,
       MCV NVARCHAR(255) null,
       RBC NVARCHAR(255) null,
       WBC NVARCHAR(255) null,
       PLT NVARCHAR(255) null,
       XueTong NVARCHAR(255) null,
       XueQing NVARCHAR(255) null,
       Glu0M NVARCHAR(255) null,
       Glu30M NVARCHAR(255) null,
       Glu60M NVARCHAR(255) null,
       Glu120M NVARCHAR(255) null,
       Glu180M NVARCHAR(255) null,
       Insulin0M NVARCHAR(255) null,
       Insulin30M NVARCHAR(255) null,
       Insulin60M NVARCHAR(255) null,
       Insulin120M NVARCHAR(255) null,
       Insulin180M NVARCHAR(255) null,
       CTai0M NVARCHAR(255) null,
       CTai30M NVARCHAR(255) null,
       CTai60M NVARCHAR(255) null,
       CTai120M NVARCHAR(255) null,
       CTai180M NVARCHAR(255) null,
       Glucagon0M NVARCHAR(255) null,
       Glucagon30M NVARCHAR(255) null,
       Glucagon60M NVARCHAR(255) null,
       Glucagon120M NVARCHAR(255) null,
       Glucagon180M NVARCHAR(255) null,
       Gad INT null,
       Iaa INT null,
       Ica INT null,
       Cea NVARCHAR(255) null,
       Afp NVARCHAR(255) null,
       Par NVARCHAR(255) null,
       Fib NVARCHAR(255) null,
       Crp NVARCHAR(255) null,
       Tt3 NVARCHAR(255) null,
       Tt4 NVARCHAR(255) null,
       Ft3 NVARCHAR(255) null,
       Ft4 NVARCHAR(255) null,
       Tsh NVARCHAR(255) null,
       Trab NVARCHAR(255) null,
       Tgab NVARCHAR(255) null,
       Tpoab NVARCHAR(255) null,
       Tg NVARCHAR(255) null,
       ExamDate DATETIME null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Examine_BloodRts (
        Id INT IDENTITY NOT NULL,
       Alt NVARCHAR(255) null,
       Ast NVARCHAR(255) null,
       Ggt NVARCHAR(255) null,
       Ua NVARCHAR(255) null,
       Bun NVARCHAR(255) null,
       Scr NVARCHAR(255) null,
       Egfr NVARCHAR(255) null,
       Tc NVARCHAR(255) null,
       Tg NVARCHAR(255) null,
       Hdl NVARCHAR(255) null,
       LDL NVARCHAR(255) null,
       ExamDate DATETIME null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Examine_Eyes (
        Id INT IDENTITY NOT NULL,
       Fundus INT null,
       VisionL NVARCHAR(255) null,
       VisionR NVARCHAR(255) null,
       CataractL INT null,
       CataractR INT null,
       GlaucomaL INT null,
       GlaucomaR INT null,
       MaculopathyL INT null,
       MaculopathyR INT null,
       RetinopathyL INT null,
       RetinopathyR INT null,
       LaserL INT null,
       LaserR INT null,
       OtherL INT null,
       OtherR INT null,
       ExamDate DATETIME null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Examine_GdPhysicals (
        Id INT IDENTITY NOT NULL,
       Weight NVARCHAR(255) null,
       BloodPressureHigh NVARCHAR(255) null,
       BloodPressureLow NVARCHAR(255) null,
       Ketone INT null,
       UrineIdoine NVARCHAR(255) null,
       UrineIdoineWeek NVARCHAR(255) null,
       Rbg NVARCHAR(255) null,
       Pbg NVARCHAR(255) null,
       Fbg50 NVARCHAR(255) null,
       Pbg1H50 NVARCHAR(255) null,
       Fbg75 NVARCHAR(255) null,
       Pbg1H75 NVARCHAR(255) null,
       Pbg2H75 NVARCHAR(255) null,
       Ins NVARCHAR(255) null,
       Ins60 NVARCHAR(255) null,
       Ins120 NVARCHAR(255) null,
       HbA1c NVARCHAR(255) null,
       HbA1cWeek NVARCHAR(255) null,
       TT3 NVARCHAR(255) null,
       TT4 NVARCHAR(255) null,
       FT3 NVARCHAR(255) null,
       FT4 NVARCHAR(255) null,
       TSH NVARCHAR(255) null,
       ExamDate DATETIME null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Examine_Legs (
        Id INT IDENTITY NOT NULL,
       ColorL INT null,
       ColorR INT null,
       MouldL INT null,
       MouldR INT null,
       CallusL INT null,
       CallusR INT null,
       AnabrosisL INT null,
       AnabrosisR INT null,
       AnabrosisNameL NVARCHAR(255) null,
       AnabrosisNameR NVARCHAR(255) null,
       GangreneL INT null,
       GangreneR INT null,
       GangreneNameL NVARCHAR(255) null,
       GangreneNameR NVARCHAR(255) null,
       GangreneTypeL INT null,
       GangreneTypeR INT null,
       MalformationL INT null,
       MalformationR INT null,
       AnesthesiaL INT null,
       AnesthesiaR INT null,
       ZubDongmaiL INT null,
       ZubDongmaiR INT null,
       JinghDongmaiL INT null,
       JinghDongmaiR INT null,
       UpperBaspL NVARCHAR(255) null,
       UpperBaspR NVARCHAR(255) null,
       XianhAsbpL NVARCHAR(255) null,
       XianhAsbpR NVARCHAR(255) null,
       DpaspL NVARCHAR(255) null,
       DpaspR NVARCHAR(255) null,
       ABIL NVARCHAR(255) null,
       ABIR NVARCHAR(255) null,
       TouchFeelingL NVARCHAR(255) null,
       TouchFeelingR NVARCHAR(255) null,
       ThermalSenseL INT null,
       ThermalSenseR INT null,
       LivuL INT null,
       LivuR INT null,
       ExamDate DATETIME null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Examine_Physicals (
        Id INT IDENTITY NOT NULL,
       Height NVARCHAR(255) null,
       BMI NVARCHAR(255) null,
       Waistline NVARCHAR(255) null,
       Hipline NVARCHAR(255) null,
       WaistHipline NVARCHAR(255) null,
       ExamDate DATETIME null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Examine_TnbBasics (
        Id INT IDENTITY NOT NULL,
       BloodPressureHigh NVARCHAR(255) null,
       BloodPressureLow NVARCHAR(255) null,
       FBG NVARCHAR(255) null,
       PBG NVARCHAR(255) null,
       HbA1c NVARCHAR(255) null,
       Weight NVARCHAR(255) null,
       ExamDate DATETIME null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Examine_UnClassifieds (
        Id INT IDENTITY NOT NULL,
       ElectroCardiogram INT null,
       ElectroCardiogramInfo NVARCHAR(255) null,
       CarotidUltraL INT null,
       CarotidUltraAreaL NVARCHAR(255) null,
       CarotidUltraR INT null,
       CarotidUltraAreaR NVARCHAR(255) null,
       CarotidUltraInfo NVARCHAR(255) null,
       HeadCT INT null,
       FattyLiver INT null,
       KidneyLongL NVARCHAR(255) null,
       KidneyLongR NVARCHAR(255) null,
       KidneyWidthL NVARCHAR(255) null,
       KidneyWidthR NVARCHAR(255) null,
       KidneyHeightL NVARCHAR(255) null,
       KidneyHeightR NVARCHAR(255) null,
       BOther NVARCHAR(255) null,
       OtherInfo NVARCHAR(255) null,
       ExamDate DATETIME null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Examine_Uroscopies (
        Id INT IDENTITY NOT NULL,
       Ketone INT null,
       UrineSugar INT null,
       UrineProtein INT null,
       UCrValue NVARCHAR(255) null,
       UCrBy INT null,
       UCrUnit INT null,
       UrineProtein24H NVARCHAR(255) null,
       UrineIdoine NVARCHAR(255) null,
       ExamDate DATETIME null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       primary key (Id)
    )

    create table Health_GdIdentifications (
        Id INT IDENTITY NOT NULL,
       LastMenstrualPeriod DATETIME null,
       PregnancyWeeks NVARCHAR(255) null,
       BloodType INT null,
       Address NVARCHAR(255) null,
       Smoking INT null,
       SmokingYear NVARCHAR(255) null,
       SmokingCount NVARCHAR(255) null,
       Drink INT null,
       DrinkYear NVARCHAR(255) null,
       DrinkCapacity NVARCHAR(255) null,
       IsBreathing INT null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       NationFk INT null,
       EducationalLevelFk INT null,
       JobFk INT null,
       primary key (Id)
    )

    create table Health_Identifications (
        Id INT IDENTITY NOT NULL,
       HighestWeight NVARCHAR(255) null,
       PayType INT null,
       BloodType INT null,
       Address NVARCHAR(255) null,
       Smoking INT null,
       SmokingYear NVARCHAR(255) null,
       SmokingCount NVARCHAR(255) null,
       Drink INT null,
       DrinkYear NVARCHAR(255) null,
       DrinkCapacity NVARCHAR(255) null,
       IsBreathing INT null,
       Total INT null,
       Completed INT null,
       PatientFk INT null,
       NationFk INT null,
       EducationalLevelFk INT null,
       JobFk INT null,
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

    alter table Diseases 
        add constraint FKF77128E3C32C267D 
        foreign key (TypeFk) 
        references CommonCodes

    alter table Doctors 
        add constraint FKE7C6751AF8351AF9 
        foreign key (DepartmentFk) 
        references Departments

    alter table FollowUp_BaseLines 
        add constraint FK553A7247DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table FollowUp_BaseLines 
        add constraint FK553A72471EDB719F 
        foreign key (IdentificationFk) 
        references Health_Identifications

    alter table FollowUp_BaseLines 
        add constraint FK553A72473CFA7D55 
        foreign key (MedicalHistoryFk) 
        references Health_MedicalHistories

    alter table FollowUp_BaseLines 
        add constraint FK553A7247CA32E3DB 
        foreign key (FamilyHistoryFk) 
        references Health_FamilyHistories

    alter table FollowUp_BaseLines 
        add constraint FK553A7247F7C41F28 
        foreign key (PhysicalFk) 
        references Examine_Physicals

    alter table FollowUp_BaseLines 
        add constraint FK553A72478F6CE742 
        foreign key (UroscopyFk) 
        references Examine_Uroscopies

    alter table FollowUp_BaseLines 
        add constraint FK553A7247699847EE 
        foreign key (BloodFk) 
        references Examine_Bloods

    alter table FollowUp_BaseLines 
        add constraint FK553A724793372D32 
        foreign key (BloodRtFk) 
        references Examine_BloodRts

    alter table FollowUp_BaseLines 
        add constraint FK553A724740CE99B4 
        foreign key (TnbBasicFk) 
        references Examine_TnbBasics

    alter table FollowUp_BaseLines 
        add constraint FK553A7247F9A0D44C 
        foreign key (EyeFk) 
        references Examine_Eyes

    alter table FollowUp_BaseLines 
        add constraint FK553A7247B5B00CF0 
        foreign key (LegsFk) 
        references Examine_Legs

    alter table FollowUp_BaseLines 
        add constraint FK553A72473D28C322 
        foreign key (UnClassifiedFk) 
        references Examine_UnClassifieds

    alter table FollowUp_BaseLines 
        add constraint FK553A72478BC18947 
        foreign key (DiagnosesFk) 
        references Health_Diagnoses

    alter table FollowUp_BaseLines 
        add constraint FK553A72475A9D47FF 
        foreign key (TreatmentFk) 
        references Health_Treatments

    alter table FollowUp_BaseLines 
        add constraint FK553A7247D6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table FollowUp_BaseLines 
        add constraint FK553A72473E0B8896 
        foreign key (PracticeDoctorFk) 
        references Doctors

    alter table FollowUp_FollowUpInfos 
        add constraint FK6D2245B0DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table FollowUp_FollowUpInfos 
        add constraint FK6D2245B0D6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table FollowUp_GdBaseLines 
        add constraint FKF380ED24DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table FollowUp_GdBaseLines 
        add constraint FKF380ED24AB662E35 
        foreign key (GdIdentificationFk) 
        references Health_GdIdentifications

    alter table FollowUp_GdBaseLines 
        add constraint FKF380ED2460875523 
        foreign key (GdHistoryFk) 
        references Health_GdHistories

    alter table FollowUp_GdBaseLines 
        add constraint FKF380ED241E097907 
        foreign key (GdmRiskFk) 
        references Health_GdmRisks

    alter table FollowUp_GdBaseLines 
        add constraint FKF380ED24382AE1BC 
        foreign key (GdPhysicalFk) 
        references Examine_GdPhysicals

    alter table FollowUp_GdBaseLines 
        add constraint FKF380ED2493372D32 
        foreign key (BloodRtFk) 
        references Examine_BloodRts

    alter table FollowUp_GdBaseLines 
        add constraint FKF380ED245A9D47FF 
        foreign key (TreatmentFk) 
        references Health_Treatments

    alter table FollowUp_GdBaseLines 
        add constraint FKF380ED24D6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table FollowUp_GdBaseLines 
        add constraint FKF380ED243E0B8896 
        foreign key (PracticeDoctorFk) 
        references Doctors

    alter table Health_Clinics 
        add constraint FKF73FB54ADA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_Educations 
        add constraint FK9E18A22DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_EvaluationScales 
        add constraint FKFC73D884DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_HealthEducations 
        add constraint FK39325254DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_MedicalHistoryItems 
        add constraint FKF7D05A583CFA7D55 
        foreign key (MedicalHistoryFk) 
        references Health_MedicalHistories

    alter table Health_Medicates 
        add constraint FKFC02C71C72964841 
        foreign key (MedicineFk) 
        references Medicines

    alter table Health_Medicates 
        add constraint FKFC02C71C5A9D47FF 
        foreign key (TreatmentFk) 
        references Health_Treatments

    alter table LeaveWords 
        add constraint FK6E98B8C6DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table LeaveWords 
        add constraint FK6E98B8C6D6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table Medicines 
        add constraint FKE12568EBABD2AA01 
        foreign key (MedicineTypeFk) 
        references CommonCodes

    alter table Notices 
        add constraint FK56BD950DE1C339DF 
        foreign key (ColumnTypeFk) 
        references CommonCodes

    alter table Notices 
        add constraint FK56BD950DD6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table Patients 
        add constraint FKB4C281C0490E7C04 
        foreign key (AreaFk) 
        references CommonCodes

    alter table Patients 
        add constraint FKB4C281C095CA8F8C 
        foreign key (DiabetesFk) 
        references CommonCodes

    alter table Patients 
        add constraint FKB4C281C0D6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table SysLogs 
        add constraint FK9200792CD6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table FollowUp_Annuals 
        add constraint FK63FF7E89F7C41F28 
        foreign key (PhysicalFk) 
        references Examine_Physicals

    alter table FollowUp_Annuals 
        add constraint FK63FF7E898F6CE742 
        foreign key (UroscopyFk) 
        references Examine_Uroscopies

    alter table FollowUp_Annuals 
        add constraint FK63FF7E89699847EE 
        foreign key (BloodFk) 
        references Examine_Bloods

    alter table FollowUp_Annuals 
        add constraint FK63FF7E8993372D32 
        foreign key (BloodRtFk) 
        references Examine_BloodRts

    alter table FollowUp_Annuals 
        add constraint FK63FF7E89F9A0D44C 
        foreign key (EyeFk) 
        references Examine_Eyes

    alter table FollowUp_Annuals 
        add constraint FK63FF7E89B5B00CF0 
        foreign key (LegsFk) 
        references Examine_Legs

    alter table FollowUp_Annuals 
        add constraint FK63FF7E893D28C322 
        foreign key (UnClassifiedFk) 
        references Examine_UnClassifieds

    alter table FollowUp_Annuals 
        add constraint FK63FF7E898BC18947 
        foreign key (DiagnosesFk) 
        references Health_Diagnoses

    alter table FollowUp_Annuals 
        add constraint FK63FF7E895A9D47FF 
        foreign key (TreatmentFk) 
        references Health_Treatments

    alter table FollowUp_Annuals 
        add constraint FK63FF7E89DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table FollowUp_Annuals 
        add constraint FK63FF7E8940CE99B4 
        foreign key (TnbBasicFk) 
        references Examine_TnbBasics

    alter table FollowUp_Annuals 
        add constraint FK63FF7E89D6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table FollowUp_Annuals 
        add constraint FK63FF7E893E0B8896 
        foreign key (PracticeDoctorFk) 
        references Doctors

    alter table FollowUp_FuZhens 
        add constraint FK129D843093372D32 
        foreign key (BloodRtFk) 
        references Examine_BloodRts

    alter table FollowUp_FuZhens 
        add constraint FK129D84305A9D47FF 
        foreign key (TreatmentFk) 
        references Health_Treatments

    alter table FollowUp_FuZhens 
        add constraint FK129D8430DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table FollowUp_FuZhens 
        add constraint FK129D843040CE99B4 
        foreign key (TnbBasicFk) 
        references Examine_TnbBasics

    alter table FollowUp_FuZhens 
        add constraint FK129D8430D6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table FollowUp_FuZhens 
        add constraint FK129D84303E0B8896 
        foreign key (PracticeDoctorFk) 
        references Doctors

    alter table FollowUp_GdFollowUps 
        add constraint FK720AA0C3DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table FollowUp_GdFollowUps 
        add constraint FK720AA0C3D6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table FollowUp_GdFollowUps 
        add constraint FK720AA0C33E0B8896 
        foreign key (PracticeDoctorFk) 
        references Doctors

    alter table FollowUp_ChanHous 
        add constraint FKD5A7BAA67CD1E4BB 
        foreign key (GdFollowUpFk) 
        references FollowUp_GdFollowUps

    alter table FollowUp_ChanHous 
        add constraint FKD5A7BAA695CA8F8C 
        foreign key (DiabetesFk) 
        references CommonCodes

    alter table FollowUp_YunZhongs 
        add constraint FK3B130A3C7CD1E4BB 
        foreign key (GdFollowUpFk) 
        references FollowUp_GdFollowUps

    alter table FollowUp_YunZhongs 
        add constraint FK3B130A3C5A9D47FF 
        foreign key (TreatmentFk) 
        references Health_Treatments

    alter table FollowUp_TiaoZhengs 
        add constraint FKD45386955A9D47FF 
        foreign key (TreatmentFk) 
        references Health_Treatments

    alter table FollowUp_TiaoZhengs 
        add constraint FKD4538695DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table FollowUp_TiaoZhengs 
        add constraint FKD453869540CE99B4 
        foreign key (TnbBasicFk) 
        references Examine_TnbBasics

    alter table FollowUp_TiaoZhengs 
        add constraint FKD4538695D6D25AF7 
        foreign key (DoctorFk) 
        references Doctors

    alter table FollowUp_TiaoZhengs 
        add constraint FKD45386953E0B8896 
        foreign key (PracticeDoctorFk) 
        references Doctors

    alter table Health_Diagnoses 
        add constraint FK8DFDE96EDA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_Diagnoses 
        add constraint FK8DFDE96E95CA8F8C 
        foreign key (DiabetesFk) 
        references CommonCodes

    alter table Health_Diagnoses_Diseases 
        add constraint FKDD19CCFC24756E05 
        foreign key (DiseaseFk) 
        references Diseases

    alter table Health_Diagnoses_Diseases 
        add constraint FKDD19CCFC8BC18947 
        foreign key (DiagnosesFk) 
        references Health_Diagnoses

    alter table Health_FamilyHistories 
        add constraint FK859640FDDA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_GdHistories 
        add constraint FKF160676ADA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_GdmRisks 
        add constraint FK749F796DDA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_MedicalHistories 
        add constraint FK9A15E50CDA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_Treatments 
        add constraint FK3912AA74DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Examine_Bloods 
        add constraint FKE5140ED9DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Examine_BloodRts 
        add constraint FKCDB7983FDA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Examine_Eyes 
        add constraint FK4FFE4744DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Examine_GdPhysicals 
        add constraint FK36CD1F8DDA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Examine_Legs 
        add constraint FK52C85259DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Examine_Physicals 
        add constraint FK9CEABC28DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Examine_TnbBasics 
        add constraint FKEA222FF1DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Examine_UnClassifieds 
        add constraint FKBB58FC1DDA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Examine_Uroscopies 
        add constraint FKD2C1AF4ADA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_GdIdentifications 
        add constraint FKAA82317DDA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_GdIdentifications 
        add constraint FKAA82317DB81319C2 
        foreign key (NationFk) 
        references CommonCodes

    alter table Health_GdIdentifications 
        add constraint FKAA82317D8AB4E3CE 
        foreign key (EducationalLevelFk) 
        references CommonCodes

    alter table Health_GdIdentifications 
        add constraint FKAA82317D3685B55E 
        foreign key (JobFk) 
        references CommonCodes

    alter table Health_Identifications 
        add constraint FKBE5BD5C0DA46D749 
        foreign key (PatientFk) 
        references Patients

    alter table Health_Identifications 
        add constraint FKBE5BD5C0B81319C2 
        foreign key (NationFk) 
        references CommonCodes

    alter table Health_Identifications 
        add constraint FKBE5BD5C08AB4E3CE 
        foreign key (EducationalLevelFk) 
        references CommonCodes

    alter table Health_Identifications 
        add constraint FKBE5BD5C03685B55E 
        foreign key (JobFk) 
        references CommonCodes
