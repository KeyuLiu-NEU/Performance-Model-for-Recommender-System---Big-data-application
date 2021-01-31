import org.apache.mahout.cf.taste.impl.model.MemoryIDMigrator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;


import java.io.File;
import java.io.IOException;


public class CustomFileDataModel extends FileDataModel {

    public MemoryIDMigrator memoryIDMigrator;
    private boolean loaded;

    public CustomFileDataModel(File dataFile) throws IOException {
        super(dataFile);
    }

    @Override
    protected long readUserIDFromString(String stringID) {
        if (!this.loaded) {
            this.memoryIDMigrator = new MemoryIDMigrator();
            this.loaded = true;
        }
        long result = this.memoryIDMigrator.toLongID(stringID);
        this.memoryIDMigrator.storeMapping(result, stringID);
        return result;
    }

}