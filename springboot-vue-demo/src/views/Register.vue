<template>
    <div style="width: 100%; height: 100vh; background-color: darkslateblue; overflow: hidden">
        <div style="width: 400px; margin: 100px auto">
            <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px 0">欢迎注册</div>
            <el-form ref="form" :model="form" size="normal" :rules="rules">
                <el-form-item prop="username">
                    <el-input :prefix-icon="avatar" v-model="form.username" />
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="lock" v-model="form.password" show-password/>
                </el-form-item>
                <el-form-item prop="confirm">
                    <el-input :prefix-icon="lock" v-model="form.confirm" show-password/>
                </el-form-item>
                <el-form-item>
                    <el-button style="width: 100%;" type="primary" @click="register">注 册</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import avatar from "@element-plus/icons/lib/Avatar";
import lock from "@element-plus/icons/lib/Lock";
import request from "@/utils/request";
export default {
    name: "Register",
    computed: {
        lock() {
            return lock
        },
        avatar() {
            return avatar
        }
    },
    data() {
        return {
            form: {},
            rules: {
                username: [
                    {required: true, message: '请输入用户名', trigger: 'blur'},
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                ],
                confirm: [
                    {required: true, message: '请输入确认密码', trigger: 'blur'},
                ],
            }
        }
    },
    methods: {
        register() {
            if(this.form.password !== this.form.confirm) {
                this.$message({
                    type: "error",
                    message: "两次密码的输入不一样"
                })
            }
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    var self = this;
                    request.post("/user/register", this.form).then(res => {
                        if(res.code === '0') {
                            this.$message({
                                type: "success",
                                message: "注册成功"
                            })
                            self.$router.push("/")
                        } else {
                            this.$message({
                                type: "error",
                                message: res.msg
                            })
                        }
                    })
                }
            })
        }
    }
}
</script>

<style scoped>

</style>