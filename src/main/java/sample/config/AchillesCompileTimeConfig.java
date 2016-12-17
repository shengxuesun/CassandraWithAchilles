package sample.config;

import info.archinnov.achilles.annotations.CompileTimeConfig;
import info.archinnov.achilles.type.CassandraVersion;

/**
 * @author Andrii Duplyk
 *
 */
@CompileTimeConfig(cassandraVersion = CassandraVersion.CASSANDRA_3_7)
public interface AchillesCompileTimeConfig {

}
