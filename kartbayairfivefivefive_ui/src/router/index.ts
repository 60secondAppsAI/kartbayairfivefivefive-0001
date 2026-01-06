import { createRouter, createWebHistory } from 'vue-router';
import { useAuth } from '../stores/auth';
import SignIn from '../components/SignIn.vue';
import Settings from '../components/Settings.vue';
import MyDashboard from '../components/MyDashboard.vue';

// Dynamic imports for module components
import Listings from '../components/Listings.vue';
import ListingDetail from '../components/ListingDetail.vue';
import Hosts from '../components/Hosts.vue';
import HostDetail from '../components/HostDetail.vue';
import Bookings from '../components/Bookings.vue';
import BookingDetail from '../components/BookingDetail.vue';
import Users from '../components/Users.vue';
import UserDetail from '../components/UserDetail.vue';
import Reviews from '../components/Reviews.vue';
import ReviewDetail from '../components/ReviewDetail.vue';
import Payments from '../components/Payments.vue';
import PaymentDetail from '../components/PaymentDetail.vue';
import Messages from '../components/Messages.vue';
import MessageDetail from '../components/MessageDetail.vue';
import Addresss from '../components/Addresss.vue';
import AddressDetail from '../components/AddressDetail.vue';
import Amenitys from '../components/Amenitys.vue';
import AmenityDetail from '../components/AmenityDetail.vue';
import ListingAmenitys from '../components/ListingAmenitys.vue';
import ListingAmenityDetail from '../components/ListingAmenityDetail.vue';
import Images from '../components/Images.vue';
import ImageDetail from '../components/ImageDetail.vue';
import Calendars from '../components/Calendars.vue';
import CalendarDetail from '../components/CalendarDetail.vue';
import ListingRules from '../components/ListingRules.vue';
import ListingRuleDetail from '../components/ListingRuleDetail.vue';
import HostResponses from '../components/HostResponses.vue';
import HostResponseDetail from '../components/HostResponseDetail.vue';
import Discounts from '../components/Discounts.vue';
import DiscountDetail from '../components/DiscountDetail.vue';
import Insurances from '../components/Insurances.vue';
import InsuranceDetail from '../components/InsuranceDetail.vue';
import Events from '../components/Events.vue';
import EventDetail from '../components/EventDetail.vue';
import CleaningServices from '../components/CleaningServices.vue';
import CleaningServiceDetail from '../components/CleaningServiceDetail.vue';
import ConflictResolutions from '../components/ConflictResolutions.vue';
import ConflictResolutionDetail from '../components/ConflictResolutionDetail.vue';
import Promotions from '../components/Promotions.vue';
import PromotionDetail from '../components/PromotionDetail.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: MyDashboard
  },
  
  // Dynamic module routes
  {
    path: '/listings',
    name: 'Listings',
    component: Listings
  },
  {
    path: '/listing/:id',
    name: 'ListingDetail',
    component: ListingDetail,
    props: true
  },
  {
    path: '/hosts',
    name: 'Hosts',
    component: Hosts
  },
  {
    path: '/host/:id',
    name: 'HostDetail',
    component: HostDetail,
    props: true
  },
  {
    path: '/bookings',
    name: 'Bookings',
    component: Bookings
  },
  {
    path: '/booking/:id',
    name: 'BookingDetail',
    component: BookingDetail,
    props: true
  },
  {
    path: '/users',
    name: 'Users',
    component: Users
  },
  {
    path: '/user/:id',
    name: 'UserDetail',
    component: UserDetail,
    props: true
  },
  {
    path: '/reviews',
    name: 'Reviews',
    component: Reviews
  },
  {
    path: '/review/:id',
    name: 'ReviewDetail',
    component: ReviewDetail,
    props: true
  },
  {
    path: '/payments',
    name: 'Payments',
    component: Payments
  },
  {
    path: '/payment/:id',
    name: 'PaymentDetail',
    component: PaymentDetail,
    props: true
  },
  {
    path: '/messages',
    name: 'Messages',
    component: Messages
  },
  {
    path: '/message/:id',
    name: 'MessageDetail',
    component: MessageDetail,
    props: true
  },
  {
    path: '/addresss',
    name: 'Addresss',
    component: Addresss
  },
  {
    path: '/address/:id',
    name: 'AddressDetail',
    component: AddressDetail,
    props: true
  },
  {
    path: '/amenitys',
    name: 'Amenitys',
    component: Amenitys
  },
  {
    path: '/amenity/:id',
    name: 'AmenityDetail',
    component: AmenityDetail,
    props: true
  },
  {
    path: '/listingAmenitys',
    name: 'ListingAmenitys',
    component: ListingAmenitys
  },
  {
    path: '/listingAmenity/:id',
    name: 'ListingAmenityDetail',
    component: ListingAmenityDetail,
    props: true
  },
  {
    path: '/images',
    name: 'Images',
    component: Images
  },
  {
    path: '/image/:id',
    name: 'ImageDetail',
    component: ImageDetail,
    props: true
  },
  {
    path: '/calendars',
    name: 'Calendars',
    component: Calendars
  },
  {
    path: '/calendar/:id',
    name: 'CalendarDetail',
    component: CalendarDetail,
    props: true
  },
  {
    path: '/listingRules',
    name: 'ListingRules',
    component: ListingRules
  },
  {
    path: '/listingRule/:id',
    name: 'ListingRuleDetail',
    component: ListingRuleDetail,
    props: true
  },
  {
    path: '/hostResponses',
    name: 'HostResponses',
    component: HostResponses
  },
  {
    path: '/hostResponse/:id',
    name: 'HostResponseDetail',
    component: HostResponseDetail,
    props: true
  },
  {
    path: '/discounts',
    name: 'Discounts',
    component: Discounts
  },
  {
    path: '/discount/:id',
    name: 'DiscountDetail',
    component: DiscountDetail,
    props: true
  },
  {
    path: '/insurances',
    name: 'Insurances',
    component: Insurances
  },
  {
    path: '/insurance/:id',
    name: 'InsuranceDetail',
    component: InsuranceDetail,
    props: true
  },
  {
    path: '/events',
    name: 'Events',
    component: Events
  },
  {
    path: '/event/:id',
    name: 'EventDetail',
    component: EventDetail,
    props: true
  },
  {
    path: '/cleaningServices',
    name: 'CleaningServices',
    component: CleaningServices
  },
  {
    path: '/cleaningService/:id',
    name: 'CleaningServiceDetail',
    component: CleaningServiceDetail,
    props: true
  },
  {
    path: '/conflictResolutions',
    name: 'ConflictResolutions',
    component: ConflictResolutions
  },
  {
    path: '/conflictResolution/:id',
    name: 'ConflictResolutionDetail',
    component: ConflictResolutionDetail,
    props: true
  },
  {
    path: '/promotions',
    name: 'Promotions',
    component: Promotions
  },
  {
    path: '/promotion/:id',
    name: 'PromotionDetail',
    component: PromotionDetail,
    props: true
  },

  // Core application routes
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
//  {
//    path: '/users',
//    name: 'Users',
//    component: Users,
//    meta: { requiresAuth: true }
//  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    meta: { requiresAuth: true }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0 };
  }
});

// Navigation guard for authentication
router.beforeEach((to, from, next) => {
  const auth = useAuth();
  
  if (to.meta.requiresAuth && !auth.isAuthenticated) {
    next({ name: 'SignIn', query: { redirect: to.fullPath } });
  } else if (to.name === 'SignIn' && auth.isAuthenticated) {
    next({ name: 'Dashboard' });
  } else {
    next();
  }
});

export default router;
