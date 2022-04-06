<template>
  <q-layout>
    <q-page-container>
      <q-page style="height: 100%; min-height: 100vh;background-image: linear-gradient(135deg,#667eea,#764ba2)!important" >
          <q-card flat bordered class="fixed-center bg-white" style="width:40%">
            <q-card-section>
              <div class="text-h5" style="text-align: center;color:#667eea">Save Heart</div>
            </q-card-section>

              <q-form autofocus id="basic-login-form" @submit="onSubmit">
                <q-card-section>
                  <label>Email*</label>
                  <q-input
                    outlined
                    dense
                    ref="userNameInput"
                    v-model="userName"
                    name="userName"
                    placeholder="Enter username"
                    :error="v$.userName.$error"
                    :error-message="errorFieldName(this.v$.userName, 'Email')"
                  />

                  <label>First Name*</label>
                  <q-input
                    outlined
                    dense
                    ref="firstNameInput"
                    v-model="firstName"
                    name="firstName"
                    placeholder="Enter First Name"
                    :error="v$.firstName.$error"
                    :error-message="errorFieldName(this.v$.firstName, 'First Name')"
                  />

                  <label>Last Name*</label>
                  <q-input
                    outlined
                    dense
                    ref="lastNameInput"
                    v-model="lastName"
                    name="lastName"
                    placeholder="Enter Last Name"
                    :error="v$.lastName.$error"
                    :error-message="errorFieldName(this.v$.lastName, 'Last Name')"
                  />
                  <label>Password*</label>
                  <q-input
                    outlined
                    dense
                    ref="passwordInput"
                    v-model="password"
                    name="userPassword"
                    :type="isPwdVisible ? 'password' : 'text'"
                    placeholder="Enter password"
                    :error="v$.password.$error"
                    :error-message="errorFieldName(this.v$.password, 'Password')"
                  >
                    <template v-slot:append>
                      <q-icon
                        :name="isPwdVisible ? 'far fa-eye' : 'far fa-eye-slash'"
                        class="cursor-pointer"
                        style="font-size: 1.3rem"
                        @click="isPwdVisible = !isPwdVisible"
                      />
                    </template>
                  </q-input>

                  <label>Confirm Password*</label>
                  <q-input
                    outlined
                    dense
                    ref="confirmPasswordInput"
                    v-model="confirmPassword"
                    name="userConfirmPassword"
                    :type="isConfirmPwdVisible ? 'password' : 'text'"
                    placeholder="Enter password"
                    :error="v$.confirmPassword.$error"
                    :error-message="errorFieldName(this.v$.confirmPassword, 'Confirm Password')"
                  >
                    <template v-slot:append>
                      <q-icon
                        :name="isConfirmPwdVisible ? 'far fa-eye' : 'far fa-eye-slash'"
                        class="cursor-pointer"
                        style="font-size: 1.3rem"
                        @click="isConfirmPwdVisible = !isConfirmPwdVisible"
                      />
                    </template>
                  </q-input>
                </q-card-section>

                <q-card-section class="q-pt-none" style="justify-content:center;display: flex;flex-direction: column">
                  <q-btn
                    unelevated
                    no-caps
                    color="primary"
                    type="submit"
                    label="Sign Up"
                  />
                  <q-btn to="/login" flat size="sm" label="Already have an account?" color="danger" style="text-align: right"></q-btn>
                </q-card-section>
              </q-form>
          </q-card>
      </q-page>
    </q-page-container>
  </q-layout>
</template>
<script>
import useVuelidate from '@vuelidate/core'
import { required, email } from '@vuelidate/validators'
import { registerApi } from '../services/loginService'
import { errorsMixin } from '../mixins/errorsMixins'
import { toastMessageMixins } from 'src/mixins/toastMessageMixins'
import { APP_BASE_URL } from 'src/constants'
export default {
  mixins: [errorsMixin, toastMessageMixins],
  name: 'RegisterPage',
  setup () {
    return {
      v$: useVuelidate()
    }
  },
  data () {
    return {
      userName: undefined,
      password: undefined,
      isPwdVisible: true,
      confirmPassword: undefined,
      lastName: undefined,
      firstName: undefined,
      isConfirmPwdVisible: true
    }
  },
  validations () {
    return {
      userName: {
        required,
        email
      },
      password: {
        required
      },
      firstName: {
        required
      },
      lastName: {
        required
      },
      confirmPassword: {
        required
      }
    }
  },
  methods: {
    onSubmit () {
      this.v$.userName.$touch()
      this.v$.password.$touch()
      this.v$.firstName.$touch()
      this.v$.lastName.$touch()

      if (!this.v$.$invalid) {
        if (this.confirmPassword === this.password) {
          const queryData = {
            email: this.userName,
            fname: this.firstName,
            lname: this.lastName,
            password: this.password
          }
          registerApi(queryData)
            .then(responeData => {
              this.successToast('Registration successful!')
              window.location.href = APP_BASE_URL + '/#/login'
            }).catch(error => {
              console.log('>>>>error', error)
              // this.handleError(error)
            })
        } else {
          this.errorToast('Confirm Passwrod and Password field must be same!')
        }
      }
    }
  }
}
</script>
