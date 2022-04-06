<template>

  <div class="row justify-center text-center">
    <div class="justify-center text-center q-mt-xl-xl">
      <AccessDenied v-if="$route.name==='AccessDenied'"/>
      <NotFound v-else-if="$route.name==='NotFound'"/>
      <template v-else>Something went wrong!</template>
      <q-btn color="primary"
             dense no-caps
             @click="redirectToDashboard"
             :label="authorized ? 'Go back to Dashboard' : 'Sign In'" >

      </q-btn>

    </div>
  </div>

</template>
<script>
import { errorsMixin } from '@/mixins/errorsMixins'
import { CookieGet } from '../lib/cookie'
import AccessDenied from './AccessDenied'
import NotFound from './NotFound'
import { DASHBOARD_PAGE_URL, LOGIN_PAGE_URL, APP_BASE_URL } from '../constants'

export default {
  name: 'error-page',
  components: { NotFound, AccessDenied },
  mixins: [errorsMixin],
  data () {
    return {
      AUTH_URL: APP_BASE_URL,
      authorized: CookieGet('access_token')
    }
  },
  methods: {
    redirectToDashboard () {
      if (this.authorized) {
        window.location.href = DASHBOARD_PAGE_URL
      } else {
        window.location.href = LOGIN_PAGE_URL
      }
    }
  }
}
</script>
