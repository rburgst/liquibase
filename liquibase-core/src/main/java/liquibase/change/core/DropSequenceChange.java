package liquibase.change.core;

import liquibase.change.AbstractChange;
import liquibase.change.DatabaseChange;
import liquibase.change.ChangeMetaData;
import liquibase.change.DatabaseChangeProperty;
import liquibase.database.Database;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.DropSequenceStatement;
import liquibase.structure.core.Sequence;

/**
 * Drops an existing sequence.
 */
@DatabaseChange(name="dropSequence", description = "Drop Sequence", priority = ChangeMetaData.PRIORITY_DEFAULT, appliesTo = "sequence")
public class DropSequenceChange extends AbstractChange {

    private String catalogName;
    private String schemaName;
    private String sequenceName;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    @DatabaseChangeProperty(mustEqualExisting ="sequence.schema")
    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    @DatabaseChangeProperty(requiredForDatabase = "all", mustEqualExisting = "sequence")
    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public SqlStatement[] generateStatements(Database database) {
        return new SqlStatement[]{new DropSequenceStatement(getCatalogName(), getSchemaName(), getSequenceName())};
    }

    public String getConfirmationMessage() {
        return "Sequence " + getSequenceName() + " dropped";
    }
}
