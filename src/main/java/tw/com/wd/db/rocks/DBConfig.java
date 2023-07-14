package tw.com.wd.db.rocks;

public class DBConfig {
    public static final String getDBRootPath() {
        return System.getProperty("core.db.path", "/tmp/rocksdb");
    }
}