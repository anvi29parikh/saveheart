<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>

        <q-toolbar-title>
          Save Heart
        </q-toolbar-title>
        <div>
          Hello, {{getUserProfileDetails[0].fname}} {{getUserProfileDetails[0].lname}}
        <q-btn icon="fa-solid fa-power-off" flat @click="logout">
          <q-tooltip>Logout</q-tooltip>
        </q-btn>
        </div>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view/>
    </q-page-container>
  </q-layout>
</template>

<script>
import { defineComponent } from 'vue'
import { mapGetters } from 'vuex'
import { CookieRemove } from 'src/lib/cookie'
import { LOGIN_PAGE_URL } from 'src/constants'

export default defineComponent({
  name: 'MainLayout',

  computed: {
    ...mapGetters('authStore', ['getUserProfileDetails'])
  },
  methods: {
    logout () {
      CookieRemove()
      window.location.href = LOGIN_PAGE_URL
    }
  }
})
</script>
