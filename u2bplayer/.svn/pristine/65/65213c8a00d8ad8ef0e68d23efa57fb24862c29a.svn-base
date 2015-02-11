package com.ijiaban.uitls;

import java.io.IOException;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.ijiaban.fragment.MainFragment;

public class GetChannelListInForeground extends AbstractGetChannelList{

	public GetChannelListInForeground(MainFragment mFragment, String mScope,
			String mEmail) {
		super(mFragment, mScope, mEmail);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String fetchToken() throws IOException {

	      try {
	          return GoogleAuthUtil.getToken(mFragment.getActivity(), mEmail, mScope);
	      } catch (UserRecoverableAuthException userRecoverableException) {
	          // GooglePlayServices.apk is either old, disabled, or not present, which is
	          // recoverable, so we need to show the user some UI through the activity.
	          mFragment.handleException(userRecoverableException);
	      } catch (GoogleAuthException fatalException) {
	          onError("Unrecoverable error " + fatalException.getMessage(), fatalException);
	      }
	      return null;
	  
	}

}
