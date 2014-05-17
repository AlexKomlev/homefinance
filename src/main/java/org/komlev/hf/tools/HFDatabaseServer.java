package org.komlev.hf.tools;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.hsqldb.server.ServerAcl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;

import java.io.IOException;
import java.util.Properties;

/**
 * Service class for HSQLDB server running.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 20.04.2014.
 */
public class HFDatabaseServer implements SmartLifecycle {

    private final Logger LOGGER = LoggerFactory.getLogger(HFDatabaseServer.class);
    private Properties properties;
    private Server server;
    private boolean running = false;

    /**
     * Constructor.
     *
     * @param properties HSQLDB properties
     */
    public HFDatabaseServer(Properties properties) {
        this.properties = properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(Runnable callback) {
        stop();
        callback.run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        if (server == null) {
            LOGGER.info("Starting HSQL server.");
            server = new Server();
            try {
                server.setProperties(new HsqlProperties(properties));
                server.start();
                running = true;
            } catch (ServerAcl.AclFormatException afe) {
                LOGGER.error("Error starting HSQL server.", afe);
            } catch (IOException e) {
                LOGGER.error("Error starting HSQL server.", e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        LOGGER.info("Stopping HSQL server.");
        if (server != null) {
            server.stop();
            running = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        if (server != null)
            server.checkRunning(running);
        return running;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPhase() {
        return 0;
    }
}
