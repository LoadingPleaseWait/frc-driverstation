package org.anidev.frcds.protoold;

import org.anidev.frcds.protoold.torobot.OperationMode;

public class ControlFlags extends CommData {
	public static final int SIZE=1;
	private boolean reset=false;
	private boolean notEStop=true;
	private boolean enabled=false;
	private boolean autonomous=false;
	private boolean fmsAttached=false;
	private boolean resync=false;
	private boolean test=false;
	private boolean checkVersions=false;

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset=reset;
	}

	public boolean isNotEStop() {
		return notEStop;
	}

	public void setNotEStop(boolean notEStop) {
		this.notEStop=notEStop;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled=enabled;
	}

	public boolean isAutonomous() {
		return autonomous;
	}

	public void setAutonomous(boolean autonomous) {
		this.autonomous=autonomous;
		if(autonomous) {
			this.test=false;
		}
	}

	public boolean isFmsAttached() {
		return fmsAttached;
	}

	public void setFmsAttached(boolean fmsAttached) {
		this.fmsAttached=fmsAttached;
	}

	public boolean isResync() {
		return resync;
	}

	public void setResync(boolean resync) {
		this.resync=resync;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test=test;
		if(test) {
			this.autonomous=false;
		}
	}

	public boolean isCheckVersions() {
		return checkVersions;
	}

	public void setCheckVersions(boolean checkVersions) {
		this.checkVersions=checkVersions;
	}
	
	public OperationMode getOperationMode() {
		if(autonomous) {
			return OperationMode.AUTONOMOUS;
		} else if(test) {
			return OperationMode.TEST;
		} else {
			return OperationMode.TELEOPERATED;
		}
	}
	
	public void setOperationMode(OperationMode mode) {
		autonomous=OperationMode.AUTONOMOUS.equals(mode);
		test=OperationMode.TEST.equals(mode);
	}

	@Override
	public byte[] serialize() {
		return serialize(DataDir.TOROBOT);
	}

	public byte[] serialize(DataDir dir) {
		int data=-1;
		if(dir==DataDir.TOROBOT) {
			data=bitsToInts(new boolean[] {reset,notEStop,enabled,
					autonomous,fmsAttached,resync,test,checkVersions})[0];
		} else {
			data=bitsToInts(new boolean[] {checkVersions,test,resync,
					fmsAttached,autonomous,enabled,notEStop,reset})[0];
		}
		return new byte[] {(byte)data};
	}

	@Override
	public void deserialize(byte[] data) {
		deserialize(data,DataDir.TOROBOT);
	}

	public void deserialize(byte[] data,DataDir dir) {
		boolean[] flags=intsToBits(new int[] {data[0]&0xFF});
		if(dir==DataDir.TOROBOT) {
			reset=flags[0];
			notEStop=flags[1];
			enabled=flags[2];
			autonomous=flags[3];
			fmsAttached=flags[4];
			resync=flags[5];
			test=flags[6];
			checkVersions=flags[7];
		} else {
			// TODO Figure out if this is accurate...
			// If it is, then its just the opposite of above
			checkVersions=flags[0];
			test=flags[1];
			resync=flags[2];
			fmsAttached=flags[3];
			autonomous=flags[4];
			enabled=flags[5];
			notEStop=flags[6];
			reset=flags[7];
		}
	}

	@Override
	public int hashCode() {
		final int prime=31;
		int result=1;
		result=prime*result+(autonomous?1231:1237);
		result=prime*result+(checkVersions?1231:1237);
		result=prime*result+(enabled?1231:1237);
		result=prime*result+(fmsAttached?1231:1237);
		result=prime*result+(notEStop?1231:1237);
		result=prime*result+(reset?1231:1237);
		result=prime*result+(resync?1231:1237);
		result=prime*result+(test?1231:1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		if(getClass()!=obj.getClass()) {
			return false;
		}
		ControlFlags other=(ControlFlags)obj;
		if(autonomous!=other.autonomous) {
			return false;
		}
		if(checkVersions!=other.checkVersions) {
			return false;
		}
		if(enabled!=other.enabled) {
			return false;
		}
		if(fmsAttached!=other.fmsAttached) {
			return false;
		}
		if(notEStop!=other.notEStop) {
			return false;
		}
		if(reset!=other.reset) {
			return false;
		}
		if(resync!=other.resync) {
			return false;
		}
		if(test!=other.test) {
			return false;
		}
		return true;
	}
}
