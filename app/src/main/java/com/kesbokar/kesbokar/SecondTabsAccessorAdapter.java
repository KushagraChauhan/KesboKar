package com.kesbokar.kesbokar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SecondTabsAccessorAdapter extends FragmentPagerAdapter {

    public SecondTabsAccessorAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {
        switch (i)
        {
            case 0:
                CarDetailsFragment carDetailsFragment = new CarDetailsFragment();
                return  carDetailsFragment;

            case 1:
                BasicInfoFragment basicInfoFragment = new BasicInfoFragment();
                return basicInfoFragment;


            case 2:
                ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
                return contactDetailsFragment;

            case 3:
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                return descriptionFragment;

            case 4:
                PhotosFragment photosFragment = new PhotosFragment();
                return photosFragment;

            case 5:
                AttributeFragment attributeFragment = new AttributeFragment();
                return attributeFragment;

            case 6:
                StatusFragment statusFragment = new StatusFragment();
                return statusFragment;

            default:
                return null;
        }
    }


    @Override
    public int getCount()
    {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "CarDetails";

            case 1:
                return "BasicInfo";

            case 2:
                return "ContactDetails";

            case 3:
                return "Description";

            case 4:
                return "Photos";

            case 5:
                return "Attribute";

            case 6:
                return "Status";

            default:
                return null;
        }
    }
}
