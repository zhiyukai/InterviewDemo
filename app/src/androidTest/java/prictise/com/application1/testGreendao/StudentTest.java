package prictise.com.application1.testGreendao;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import prictise.com.application1.testGreendao.Student;
import prictise.com.application1.testGreendao.StudentDao;

public class StudentTest extends AbstractDaoTestLongPk<StudentDao, Student> {

    public StudentTest() {
        super(StudentDao.class);
    }

    @Override
    protected Student createEntity(Long key) {
        Student entity = new Student();
        entity.setStuId(key);
        return entity;
    }

    @Override
    public void testInsertInTx() {
        super.testInsertInTx();
    }

}
