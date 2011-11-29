package play.modules.envers;

import play.Logger;
import play.Play;
import play.PlayPlugin;
import play.db.jpa.JPA;

public class PlayEnversPlugin extends PlayPlugin {

    @Override
    public void onApplicationStart() {
        Logger.info("Envers module started");
    }

    @Override
    public void onConfigurationRead() {
        Logger.debug("Checking if JPA has already been initialized ...");
        if (JPA.entityManagerFactory != null) {
            Logger.error("Error : JPA has already been initialized");
        } else {
            Logger.debug("ok, JPA not yet initialized");
        }

        Logger.info("Adding Envers configuration to Play! configuration ...");

        if (Play.configuration.getProperty("hibernate.ejb.event.post-insert") == null) {
            Logger.debug("Adding property hibernate.ejb.event.post-insert");
            Play.configuration.setProperty("hibernate.ejb.event.post-insert", "org.hibernate.ejb.event.EJB3PostInsertEventListener,org.hibernate.envers.event.AuditEventListener");
        }
        if (Play.configuration.getProperty("hibernate.ejb.event.post-update") == null) {
            Logger.debug("Adding property hibernate.ejb.event.post-update");
            Play.configuration.setProperty("hibernate.ejb.event.post-update", "org.hibernate.ejb.event.EJB3PostUpdateEventListener,org.hibernate.envers.event.AuditEventListener");
        }
        if (Play.configuration.getProperty("hibernate.ejb.event.post-delete") == null) {
            Logger.debug("Adding property hibernate.ejb.event.post-delete");
            Play.configuration.setProperty("hibernate.ejb.event.post-delete", "org.hibernate.ejb.event.EJB3PostDeleteEventListener,org.hibernate.envers.event.AuditEventListener");
        }
        if (Play.configuration.getProperty("hibernate.ejb.event.pre-collection-update") == null) {
            Logger.debug("Adding property hibernate.ejb.event.pre-collection-update");
            Play.configuration.setProperty("hibernate.ejb.event.pre-collection-update", "org.hibernate.envers.event.AuditEventListener");
        }
        if (Play.configuration.getProperty("hibernate.ejb.event.pre-collection-remove") == null) {
            Logger.debug("Adding property hibernate.ejb.event.pre-collection-remove");
            Play.configuration.setProperty("hibernate.ejb.event.pre-collection-remove", "org.hibernate.envers.event.AuditEventListener");
        }
        if (Play.configuration.getProperty("hibernate.ejb.event.post-collection-recreate") == null) {
            Logger.debug("Adding property hibernate.ejb.event.post-collection-recreate");
            Play.configuration.setProperty("hibernate.ejb.event.post-collection-recreate", "org.hibernate.envers.event.AuditEventListener");
        }
    }
}
