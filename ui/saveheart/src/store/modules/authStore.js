import { getUserDetails } from 'src/services/userService'

const initialState = {
  userProfileDetails: {}
}

const state = () => ({ ...Object.assign({}, initialState) })

const getters = {
  getUserProfileDetails: _state => {
    return _state.userProfileDetails
  }
}

const mutations = {
  SET_USER_PROFILE_DETAILS (_state, userProfileDetails) {
    _state.userProfileDetails = userProfileDetails
  },
  RESET_USER_STATE (_state) {
    for (const prop in _state) {
      _state[prop] = initialState[prop]
    }
  }
}

const actions = {
  async setUserProfileDetails (context, userProfileDetails) {
    context.commit('SET_USER_PROFILE_DETAILS', userProfileDetails)
  },
  async resetUserStates (context) {
    context.commit('RESET_USER_STATE')
  },
  async setUserDetails (context) {
    const response = await getUserDetails()
    const userDetails = response && response.data ? response.data : {}

    context.commit('SET_USER_PROFILE_DETAILS', userDetails)
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
