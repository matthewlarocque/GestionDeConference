<template>
  <el-dialog :title="title" :visible.sync="visible" width="30%">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="demo-ruleForm">
      <el-form-item label="登录名" prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="真实姓名" prop="realname">
        <el-input v-model="form.realname"></el-input>
      </el-form-item>
      <el-form-item label="工号" prop="code">
        <el-input v-model="form.code"></el-input>
      </el-form-item>
      <el-form-item label="联系方式" prop="phone">
        <el-input v-model="form.phone"></el-input>
      </el-form-item>
      <el-form-item label="用户角色" prop="role">
        <el-select v-model="form.role" placeholder="请选用户角色">
          <el-option label="管理员" value="管理员"></el-option>
          <el-option label="员工" value="员工"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <el-alert v-show="id==null"
      title="新增用户默认密码：111111"
      type="info">
    </el-alert>
    <div slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取 消</el-button>
      <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { postAction,getAction} from '@api/manage';
export default {
  name: 'UserForm',
  props: {
    dialogFormVisible: Boolean,
    id:String,
    sizePerPage:Number
  },
  watch:{
    dialogFormVisible:{
      handler (newValue, oldValue) {
        this.visible=newValue;
      },
    },
    id:{
      handler (newValue, oldValue) {
        if(newValue!=null){
          this.title='编辑用户'
        }else{
          this.title='新增用户'
        }
        getAction('user/detail/'+newValue, {}).then((res) => {
          this.form.id=res.data.id;
          this.form.username=res.data.username;
          this.form.role=res.data.role;
          this.form.code=res.data.code;
          this.form.status=res.data.status;
        });
      },
    },
  },
  mounted(){
    this.form={
      id:null,
      username: '',
      realname: '',
      phone: '',
      role: undefined,
      status:'1'
    }
  },
  data() {
    return {
      visible:false,
      title:'新增用户',
      dialogTableVisible: false,
      form: {
        id:null,
        username: '',
        realname: '',
        code: '',
        phone: '',
        role: undefined,
        status:'1'
      },
      rules: {
        username: [
          { required: true, message: '请输入登录名', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              postAction('user/checkUser', this.form).then((res) => {
                if(!res.data.success){
                  return callback(new Error('登录名已存在'));
                }else{
                  callback();
                }
              });
            }, trigger: 'blur' }
        ],
        realname: [
          { required: true, message: '请输入真实姓名', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' },
        ],
        code: [
          { required: true, message: '请输入工号', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 20 个字符', trigger: 'blur' },
        ],
        phone: [
          { required: true, message: '请输入联系方式', trigger: 'blur' },
          { min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur' },
        ],
        role: [
          { required: true, message: '请选择用户角色', trigger: 'change' }
        ],
      },
    };
  },
  methods:{
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          postAction('user/saveUser', this.form).then((res) => {
            this.$message.success('保存成功')
            this.visible = false
            this.$emit('userList',{currentPage:0,sizePerPage:this.sizePerPage})
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
