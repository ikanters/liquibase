package liquibase.migrator.change;

import liquibase.database.OracleDatabase;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

public class CreateIndexChangeTest extends AbstractChangeTest {

    public void testGetRefactoringName() throws Exception {
        assertEquals("Create Index", new CreateIndexChange().getRefactoringName());
    }

    public void testGenerateStatement() throws Exception {
        CreateIndexChange refactoring = new CreateIndexChange();
        refactoring.setIndexName("IDX_TEST");
        refactoring.setTableName("TAB_NAME");

        ColumnConfig column1 = new ColumnConfig();
        column1.setName("COL1");
        refactoring.addColumn(column1);

        assertEquals("CREATE INDEX IDX_TEST ON TAB_NAME(COL1)", refactoring.generateStatements(new OracleDatabase())[0]);

        ColumnConfig column2 = new ColumnConfig();
        column2.setName("COL2");
        refactoring.addColumn(column2);

        assertEquals("CREATE INDEX IDX_TEST ON TAB_NAME(COL1, COL2)", refactoring.generateStatements(new OracleDatabase())[0]);
    }

    public void testGetConfirmationMessage() throws Exception {
        CreateIndexChange refactoring = new CreateIndexChange();
        refactoring.setIndexName("IDX_TEST");

        assertEquals("Index IDX_TEST has been created", refactoring.getConfirmationMessage());
    }

    public void testCreateNode() throws Exception {
        CreateIndexChange refactoring = new CreateIndexChange();
        refactoring.setIndexName("IDX_TEST");
        refactoring.setTableName("TAB_NAME");

        ColumnConfig column1 = new ColumnConfig();
        column1.setName("COL1");
        refactoring.addColumn(column1);

        ColumnConfig column2 = new ColumnConfig();
        column2.setName("COL2");
        refactoring.addColumn(column2);

        Element element = refactoring.createNode(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        assertEquals("createIndex", element.getTagName());
        assertEquals("IDX_TEST", element.getAttribute("indexName"));
        assertEquals("TAB_NAME", element.getAttribute("tableName"));

        assertEquals(2, element.getChildNodes().getLength());
        assertEquals("column", ((Element) element.getChildNodes().item(0)).getTagName());
        assertEquals("COL1", ((Element) element.getChildNodes().item(0)).getAttribute("name"));
        assertEquals("column", ((Element) element.getChildNodes().item(1)).getTagName());
        assertEquals("COL2", ((Element) element.getChildNodes().item(1)).getAttribute("name"));
    }

}