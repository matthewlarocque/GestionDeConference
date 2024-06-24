<template>
  <el-dialog :title="title" :visible.sync="visible" width="30%">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="demo-ruleForm">
      <el-form-item label="违规员工" prop="userid">
        <el-select v-model="form.userid" placeholder="请选员工">
          <el-option :key="item.id" :label="item.realname" :value="item.id" v-for="item in users"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="违规事由" prop="content">
        <el-select clearable v-model="form.content" placeholder="违规事由">
          <el-option value="拖延时间">拖延时间</el-option>
          <el-option value="毁坏物品">毁坏物品</el-option>
          <el-option value="其他">其他</el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="处罚方式" prop="punishment">
        <el-select clearable v-model="form.punishment" placeholder="处罚方式">
          <el-option value="口头警告">口头警告</el-option>
          <el-option value="拉黑处理">拉黑处理</el-option>
          <el-option value="其他">其他</el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input type="textarea" autosize v-model="form.remark" placeholder="备注"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取 消</el-button>
      <el-button discipline="primary" @click="submitForm">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { postAction,getAction} from '@api/manage';
export default {
  name: 'DisciplineForm',
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
          this.title='编辑违规'
        }else{
          this.title='新增违规'
        }
        getAction('discipline/detail/'+newValue, {}).then((res) => {
          this.form.id=res.data.id;
          this.form.content=res.data.content;
          this.form.datetime=res.data.datetime;
          this.form.punishment=res.data.punishment;
          this.form.remark=res.data.remark;
        });
      },
    },
  },
  mounted(){
    this.form={
      id: null,
      content: undefined,
      punishment: undefined,
      userid: undefined,
      remark:''
    }
    this.getUsers();
  },
  data() {
    return {
      visible:false,
      title:'新增违规',
      dialogTableVisible: false,
      users:[],
      form: {
        id:null,
        datetime: '',
        content: undefined,
        punishment: undefined,
        userid: undefined,
        remark:''
      },
      rules: {
        userid: [
          { required: true, message: '请选择违规员工', trigger: 'change' },
        ],
        content: [
          { required: true, message: '请选择违规事由', trigger: 'change' },
        ],
        punishment: [
          { required: true, message: '请选择处罚方式', trigger: 'change' },
        ],
      },
    };
  },
  methods:{
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          postAction('discipline/saveDiscipline', this.form).then((res) => {
            this.$message.success('保存成功')
            this.visible = false
            this.$emit('disciplineList',{currentPage:0,sizePerPage:this.sizePerPage})
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    getUsers(){
      postAction('user/findByRole', {role:'员工'}).then((res) => {
        this.users=res.data;
      });
    }
  }
}
</script>

<style scoped>

</style>
