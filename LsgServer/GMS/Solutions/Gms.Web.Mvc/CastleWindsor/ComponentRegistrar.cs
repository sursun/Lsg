﻿using Gms.Infrastructure;

namespace Gms.Web.Mvc.CastleWindsor
{
    using Castle.MicroKernel.Registration;
    using Castle.Windsor;

    using SharpArch.Domain.Commands;
    using SharpArch.Domain.Events;
    using SharpArch.Domain.PersistenceSupport;
    using SharpArch.NHibernate;
    using SharpArch.NHibernate.Contracts.Repositories;
    using SharpArch.Web.Mvc.Castle;

    public class ComponentRegistrar
    {
        public static void AddComponentsTo(IWindsorContainer container) 
        {
            AddGenericRepositoriesTo(container);
            AddCustomRepositoriesTo(container);
            AddQueryObjectsTo(container);
            AddTasksTo(container);
            AddHandlersTo(container);
        }

        private static void AddTasksTo(IWindsorContainer container)
        {
            container.Register(
                AllTypes
                    .FromAssemblyNamed("Gms.Tasks")
                    .Pick()
                    .WithService.FirstNonGenericCoreInterface("Gms.Domain"));
        }

        private static void AddCustomRepositoriesTo(IWindsorContainer container) 
        {
            container.Register(
                AllTypes
                    .FromAssemblyNamed("Gms.Infrastructure")
                    .BasedOn(typeof(IRepositoryWithTypedId<,>))
                    .WithService.FirstNonGenericCoreInterface("Gms.Infrastructure"));
            //container.Register(
            //    AllTypes
            //        .FromAssemblyNamed("Gms.Infrastructure")
            //        .BasedOn(typeof(IRepositoryBase<>))
            //        .WithService.FirstNonGenericCoreInterface("Gms.Infrastructure"));
        }

        private static void AddGenericRepositoriesTo(IWindsorContainer container)
        {
            container.Register(
                Component.For(typeof(IEntityDuplicateChecker))
                    .ImplementedBy(typeof(EntityDuplicateChecker))
                    .Named("entityDuplicateChecker"));

            container.Register(
                Component.For(typeof(INHibernateRepository<>))
                    .ImplementedBy(typeof(NHibernateRepository<>))
                    .Named("nhibernateRepositoryType")
                    .Forward(typeof(IRepository<>)));

            container.Register(
                Component.For(typeof(INHibernateRepositoryWithTypedId<,>))
                    .ImplementedBy(typeof(NHibernateRepositoryWithTypedId<,>))
                    .Named("nhibernateRepositoryWithTypedId")
                    .Forward(typeof(IRepositoryWithTypedId<,>)));

            container.Register(
                    Component.For(typeof(ISessionFactoryKeyProvider))
                        .ImplementedBy(typeof(DefaultSessionFactoryKeyProvider))
                        .Named("sessionFactoryKeyProvider"));

            container.Register(
                    Component.For(typeof(ICommandProcessor))
                        .ImplementedBy(typeof(CommandProcessor))
                        .Named("commandProcessor"));

            //container.Register(
            //    Component.For(typeof(IRepositoryBase<>))
            //        .ImplementedBy(typeof(RepositoryBase<>))
            //        .Named("RepositoryBase"));

        }

        private static void AddQueryObjectsTo(IWindsorContainer container) 
        {
            container.Register(
                AllTypes.FromAssemblyNamed("Gms.Web.Mvc")
                    .BasedOn<NHibernateQuery>()
                    .WithService.DefaultInterfaces());

            container.Register(
                AllTypes.FromAssemblyNamed("Gms.Infrastructure")
                    .BasedOn(typeof(NHibernateQuery))
                    .WithService.DefaultInterfaces());
        }

        private static void AddHandlersTo(IWindsorContainer container)
        {
            container.Register(
                AllTypes.FromAssemblyNamed("Gms.Tasks")
                    .BasedOn(typeof(ICommandHandler<>))
                    .WithService.FirstInterface());

            container.Register(
                AllTypes.FromAssemblyNamed("Gms.Tasks")
                    .BasedOn(typeof(IHandles<>))
                    .WithService.FirstInterface());
        }
    }
}