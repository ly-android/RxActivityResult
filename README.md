## RxActivityResult
> The purpose is to simplify the use of startActivityForResult.
thanks [RxPermissions](https://github.com/tbruyelle/RxPermissions)

- Step 1. Add the JitPack repository to your build filerepositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
- Step 2. Add the dependency
```gradle
dependencies {
	        implementation 'com.github.ly-android:RxActivityResult:1.0'
	}
```
### look smaple
``` java
public class MainActivity extends FragmentActivity {
  Disposable dis;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    RxActivityResult rxActivityResult = new RxActivityResult(this);
    dis = rxActivityResult.startActivityForResult(new Intent(this, TwoActivity.class), 0)
      .subscribe(new Consumer<ActivityResult>() {
        @Override
        public void accept(ActivityResult activityResult) throws Exception {
          Log.d("MainActivity", "activityResult data=" + activityResult.data);
        }
      });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (dis != null) {
      dis.dispose();
    }
  }
}
```

just call like it
```java
 RxActivityResult rxActivityResult = new RxActivityResult(this);
    rxActivityResult.startActivityForResult(new Intent(this, TwoActivity.class), 0)
      .subscribe(new Consumer<ActivityResult>() {
        @Override
        public void accept(ActivityResult activityResult) throws Exception {
          
        }
      });
```
